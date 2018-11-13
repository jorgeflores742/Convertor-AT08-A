package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.ConvertCriteria;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;
import sun.security.krb5.internal.crypto.crc32;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConvertFileVideo implements IConvertFile {

    FFmpeg ffmpeg;
    FFprobe ffprobe;
    FFmpegProbeResult in;
    static int process = 0;

    @Override
    public int convert(ConvertCriteria convertCriteria) {
        try {
            ffmpeg = new FFmpeg("lib\\filesff\\ffmpeg");
            ffprobe = new FFprobe("lib\\filesff\\ffprobe");
            in = ffprobe.probe(convertCriteria.getPathFrom());
        } catch (IOException e) {
            e.printStackTrace();
        }
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in) // Or filename
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput(convertCriteria.getPathTo()+"\\"+convertCriteria.getFileName()+"."+convertCriteria.getCnvVideoType())  // Filename for the destination

                .setFormat(convertCriteria.getCnvVideoType()) //vvat is inferred from filename, or can be set
//                .setTargetSize(250000)  // Aim for a 250KB file

//                .disableSubtitle()       // No subtiles

                .setAudioChannels(Integer.parseInt(convertCriteria.getCnvChannels()))         // Mono audio
                .setAudioCodec(convertCriteria.getCnvVideoAudioCodec())        // using the aac codec
                .setAudioSampleRate(Integer.parseInt(convertCriteria.getCnvAspectRatio()))  // at 48KHz
//                .setAudioBitRate(32768)      // at 32 kbit/s

                .setVideoCodec(convertCriteria.getCnvVideoCodec())     // Video using libx264
                .setVideoFrameRate(Integer.parseInt(convertCriteria.getCnvFps()), 1)     // at 24 frames per second
                .setVideoResolution(Integer.parseInt(convertCriteria.getCnvResolutionWidth()), Integer.parseInt(convertCriteria.getCnvResolutionHeight())) // at 640x480 resolution
                .done();
        FFmpegProbeResult finalIn = in;
        FFmpegJob job = executor.createJob(builder, new ProgressListener() {

            // Using the FFmpegProbeResult determine the duration of the input
            final double duration_ns = finalIn.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

            @Override
            public void progress(Progress progress) {


                double percentage = progress.out_time_ns / duration_ns;
                process = Integer.parseInt(String.format(
                        "%.0f",
                        percentage * 100,
                        progress.status
                ));

                // Print out interesting information about the progress
                System.out.println(String.format(
                        "[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx",
                        percentage * 100,
                        progress.status,
                        progress.frame,
                        FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                        progress.fps.doubleValue(),
                        progress.speed
                ));
            }
        });

        job.run();
        return process;
    }
}
