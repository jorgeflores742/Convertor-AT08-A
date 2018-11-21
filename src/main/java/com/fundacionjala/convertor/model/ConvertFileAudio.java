package com.fundacionjala.convertor.model;

import com.fundacionjala.convertor.controller.ConvertCriteria;
import com.fundacionjala.convertor.utils.SingleLogger;
import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFmpegUtils;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import net.bramp.ffmpeg.job.FFmpegJob;
import net.bramp.ffmpeg.probe.FFmpegProbeResult;
import net.bramp.ffmpeg.progress.Progress;
import net.bramp.ffmpeg.progress.ProgressListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ConvertFileAudio implements IConvertFile {
    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private FFmpegProbeResult in;
    private static int process;
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Override
    public int convert(ConvertCriteria convertCriteria) {
        sL.setLogger(ConvertFileAudio.class.getName());
        sL.register(null, "INFO", "Successful - convertAudio - start");
        try {
            sL.register(null, "INFO", "Successful - convert - ffmpeg/ffmprobe - start");
            ffmpeg = new FFmpeg("lib\\filesff\\ffmpeg");
            ffprobe = new FFprobe("lib\\filesff\\ffprobe");
            in = ffprobe.probe(convertCriteria.getPathFrom());
        } catch (IOException e) {
            sL.register(e, "SEVERE", "Successful - convert - ffmpeg/ffmprobe - failed");
        }
        System.out.println("input>"+convertCriteria.getPathFrom());
        System.out.println("output>"+convertCriteria.getPathTo()+"\\"+convertCriteria.getFileName()+"."+convertCriteria.getCnvAudioType());

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        String format = getCodec(convertCriteria.getCnvAudioType());
        ArrayList<Object> parameters = getParams(convertCriteria);
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in) // Or filename
                .overrideOutputFiles(true) // Override the output if it exists
                .addExtraArgs("-vn")
                .addOutput(convertCriteria.getPathTo()+"\\"+convertCriteria.getFileName()+"."+convertCriteria.getCnvAudioType())  // Filename for the destination
                .setFormat(format) //format to video PASSED
                .setAudioCodec((String) parameters.get(0))
                .setAudioChannels(Integer.parseInt((String) parameters.get(1)))
                .done();
        FFmpegProbeResult finalIn = in;
        FFmpegJob job = executor.createJob(builder, new ProgressListener() {

            // Using the FFmpegProbeResult determine the duration of the input
            final double duration_ns = finalIn.getFormat().duration * TimeUnit.SECONDS.toNanos(1);

            @Override
            public void progress(Progress progress) {
                sL.register(null, "INFO", "Successful - convert - progress - start");
                double percentage = progress.out_time_ns / duration_ns;
                process = Integer.parseInt(String.format(
                        "%.0f",
                        percentage * 100,
                        progress.status
                ));

                // Print out interesting information about the progress
                sL.register(null, "INFO", "Successful - convert - progress - start "+(String.format(
                        "[%.0f%%] status:%s frame:%d time:%s ms fps:%.0f speed:%.2fx",
                        percentage * 100,
                        progress.status,
                        progress.frame,
                        FFmpegUtils.toTimecode(progress.out_time_ns, TimeUnit.NANOSECONDS),
                        progress.fps.doubleValue(),
                        progress.speed
                )));
                sL.register(null, "INFO", "Successful - convert - progress - finished");
            }
        });

        job.run();
        sL.register(null, "INFO", "Successful - convertAudio - finished");
        return process;
    }

    private ArrayList<Object> getParams(ConvertCriteria criteria) {
        sL.register(null, "INFO", "Successful - getParams - start");
        ArrayList<Object> criteriaAux = new ArrayList<Object>() {};
        if (criteria.getCnvAudioCodec() == null) {
            System.out.println("audioCodec="+in.getStreams().get(0).codec_name);
            criteriaAux.add(in.getStreams().get(0).codec_name);
        } else {
            System.out.println("audioCodec="+criteria.getCnvAudioCodec());
            criteriaAux.add(criteria.getCnvAudioCodec());
        }

        if (criteria.getCnvChannels() == null) {
            String s = in.getStreams().get(0).channel_layout;
            System.out.println("channels="+getChannels(s));
            criteriaAux.add(getChannels(s));
        } else {
            System.out.println("channels="+getChannels(criteria.getCnvChannels()));
            criteriaAux.add(getChannels(criteria.getCnvChannels()));
        }
        sL.register(null, "INFO", "Successful - getParams - finished");
        return criteriaAux;
    }

    private String getCodec(String cnvVideoType) {
        sL.register(null, "INFO", "Successful - getCodec - start");
        String format = null;
        if (cnvVideoType.equals("null")) {
            format = "matroska";
        } else {
            format = cnvVideoType;
        }
        sL.register(null, "INFO", "Successful - getCodec - finished");
        return format;
    }

    private String getChannels(String var) {
        sL.register(null, "INFO", "Successful - getChannels - start");
        String channel = null;
        if(var.toLowerCase().equals("mono")) {
            channel = "1";
        }else{
            channel = "2";
        }
        sL.register(null, "INFO", "Successful - getChannels - finished");
        return channel;
    }
}
