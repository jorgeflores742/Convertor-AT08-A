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

import javax.swing.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ConvertFileAudio implements IConvertFile {
    static JFrame frame = new JFrame();
    static JProgressBar progBar = new JProgressBar();
    FFmpeg ffmpeg;
    FFprobe ffprobe;
    FFmpegProbeResult in;
    static int process;

    @Override
    public int convert(ConvertCriteria convertCriteria) {
        try {
            ffmpeg = new FFmpeg("lib\\filesff\\ffmpeg");
            ffprobe = new FFprobe("lib\\filesff\\ffprobe");
            in = ffprobe.probe("C:\\Users\\user\\Downloads\\Video\\PONTUVIDEO.mp4");
        } catch (IOException e) {
            e.printStackTrace();
        }
        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in) // Or filename
                .overrideOutputFiles(true)
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput("C:\\Users\\user\\Documents\\Repositorio\\output.mp4")  // Filename for the destination
                .setAudioCodec("ELCOF¡DIGO")
                .setAudioChannels(FFmpeg.AUDIO_MONO)
                .setAudioBitRate(FFmpeg.AUDIO_SAMPLE_48000)
                .setAudioSampleRate(FFmpeg.AUDIO_SAMPLE_16000)
                .done();
        FFmpegProbeResult finalIn = in;
        FFmpegJob job = executor.createJob(builder, new ProgressListener() {

            // Using the FFmpegProbeResult determine the duration of the input
            final double duration_ns = finalIn.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

            @Override
            public void progress(Progress progress) {

                progBar.setMaximum(100);
                double percentage = progress.out_time_ns / duration_ns;
                process = Integer.parseInt(String.format(
                        "%.0f",
                        percentage * 100,
                        progress.status
                ));
                progBar.setValue(Integer.parseInt(String.format(
                        "%.0f",
                        percentage * 100,
                        progress.status
                )));

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