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
import org.apache.commons.lang3.math.Fraction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ConvertFileVideo implements IConvertFile {

    private FFmpeg ffmpeg;
    private FFprobe ffprobe;
    private FFmpegProbeResult in;
    private static int process = 0;
    private static SingleLogger sL = SingleLogger.getInstanceLogger();

    @Override
    public int convert(ConvertCriteria convertCriteria) {
        sL.register(null, "INFO", "Successful - convertVideo - start");
        try {
            ffmpeg = new FFmpeg("lib\\filesff\\ffmpeg");
            ffprobe = new FFprobe("lib\\filesff\\ffprobe");
            in = ffprobe.probe(convertCriteria.getPathFrom());
        } catch (IOException e) {
            sL.register(e, "SEVERE", "Successful - convert - ffmpeg/ffmprobe - failed");
        }
        String format = getCodec(convertCriteria.getCnvVideoType());
//        System.out.println("input>" + convertCriteria.getPathFrom());
//        System.out.println("output>" + convertCriteria.getPathTo() + "\\" + convertCriteria.getFileName() + "." + convertCriteria.getCnvVideoType());

        ArrayList<Object> parameters = getParams(convertCriteria);

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);
        FFmpegBuilder builder = new FFmpegBuilder()
                .setInput(in) // Or filename
                .overrideOutputFiles(true) // Override the output if it exists
                .addOutput(convertCriteria.getPathTo() + "\\" + convertCriteria.getFileName() + "." + convertCriteria.getCnvVideoType())  // Filename for the destination
                .setFormat(format) //format to video PASSED
                .setAudioCodec((String) parameters.get(0))        // using the aac codec PASSED
                .setVideoCodec((String) parameters.get(1))     // Video using libx264 PASSED
                .setVideoFrameRate((Fraction) parameters.get(2))     // at 24 frames per second VERIFY
                .setVideoResolution((Integer)parameters.get(3), (Integer)parameters.get(4)) // at 640x480 resolution
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
        sL.register(null, "INFO", "Successful - convertVideo - finished");

        return process;
    }

    private ArrayList<Object> getParams(ConvertCriteria criteria) {
        sL.register(null, "INFO", "Successful - getParams - start");
        ArrayList<Object> criteriaAux = new ArrayList<Object>() {};
        if (criteria.getCnvVideoCodec() == null) {
            System.out.println("audioCodec="+in.getStreams().get(1).codec_name);
            criteriaAux.add(in.getStreams().get(1).codec_name);
        } else {
            System.out.println("audioCodec="+criteria.getCnvVideoCodec());
            criteriaAux.add(criteria.getCnvVideoCodec());
        }
        ;
        if (criteria.getCnvVideoAudioCodec() == null) {
            System.out.println("videoCodec="+in.getStreams().get(0).codec_name);
            criteriaAux.add(in.getStreams().get(0).codec_name);
        } else {
            System.out.println("videoCodec="+criteria.getCnvVideoAudioCodec());
            criteriaAux.add(criteria.getCnvVideoAudioCodec());
        }
        ;
        if (criteria.getCnvFps() == null) {
            int fps = in.getStreams().get(0).avg_frame_rate.getNumerator();
            float fpsFloat = fps;
            fpsFloat = fpsFloat >= 1000 ? (fpsFloat / 1000) : fpsFloat;
            System.out.println("framerate="+fpsFloat);
            criteriaAux.add(getFractionSelected(Float.toString(fpsFloat)));
        } else {
            criteriaAux.add(getFractionSelected(criteria.getCnvFps()));
        }
        ;
        if (criteria.getCnvResolutionWidth() == null) {
            int w = in.getStreams().get(0).width;
            criteriaAux.add(w);
        } else {
            criteriaAux.add(Integer.parseInt(criteria.getCnvResolutionWidth()));
        }
        ;
        if (criteria.getCnvResolutionHeight() == null) {
            int h = in.getStreams().get(0).height;
            criteriaAux.add(h);
        } else {
            criteriaAux.add(Integer.parseInt(criteria.getCnvResolutionHeight()));
        }
        sL.register(null, "INFO", "Successful - getParams - finished");
        return criteriaAux;
    }

    private String getCodec(String cnvVideoType) {
        sL.register(null, "INFO", "Successful - getCodec - start");
        String format = null;
        if (cnvVideoType.equals("mkv")) {
            format = "matroska";
        } else {
            format = cnvVideoType;
        }
        sL.register(null, "INFO", "Successful - getCodec - finished");
        return format;
    }

    private Fraction getFractionSelected(String cnvFps) {
        sL.register(null, "INFO", "Successful - getFractionSelected - start");
        Fraction f = null;
        if (cnvFps.equals("24.0")) {
            f = Fraction.getFraction(24, 1);
        } else if (cnvFps.equals("25.0")) {
            f = Fraction.getFraction(25, 1);
        } else if (cnvFps.equals("27.0")) {
            f = Fraction.getFraction(27, 1);
        } else if (cnvFps.equals("29.9")) {
            f = Fraction.getFraction(30000, 1001);
        } else if (cnvFps.equals("30.0")) {
            f = Fraction.getFraction(30, 1);
        } else if (cnvFps.equals("60.0")) {
            f = Fraction.getFraction(60, 1);
        }
        sL.register(null, "INFO", "Successful - getFractionSelected - start");
        return f;
    }
}
