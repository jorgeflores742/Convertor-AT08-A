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
import org.apache.commons.lang3.math.Fraction;

import java.io.IOException;
import java.util.ArrayList;
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

        Fraction n = null;
        if (convertCriteria.getCnvFps() != null) {
            n = getFractionSelected(convertCriteria.getCnvFps());
        }
        String format = getCodec(convertCriteria.getCnvVideoType());
        System.out.println("input>" + convertCriteria.getPathFrom());
        System.out.println("output>" + convertCriteria.getPathTo() + "\\" + convertCriteria.getFileName() + "." + convertCriteria.getCnvVideoType());

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
                .setVideoResolution(Integer.parseInt((String) parameters.get(3)), Integer.parseInt((String) parameters.get(4))) // at 640x480 resolution


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

    private ArrayList<Object> getParams(ConvertCriteria criteria) {
        ArrayList<Object> criteriaAux = new ArrayList<Object>() {};
        if (criteria.getCnvVideoCodec() == null) {
            criteriaAux.add(in.getStreams().get(1).codec_name);
        } else {
            criteriaAux.add(criteria.getCnvVideoCodec());
        }
        ;
        if (criteria.getCnvVideoAudioCodec() == null) {
            criteriaAux.add(in.getStreams().get(1).codec_name);
        } else {
            criteriaAux.add(criteria.getCnvVideoAudioCodec());
        }
        ;
        if (getFractionSelected(criteria.getCnvFps()) == null) {
            criteriaAux.add(in.getStreams().get(0).avg_frame_rate);
        } else {
            criteriaAux.add(getFractionSelected(criteria.getCnvFps()));
        }
        ;
        if (criteria.getCnvResolutionWidth() == null) {
            criteriaAux.add(in.getStreams().get(0).width);
        } else {
            criteriaAux.add(criteria.getCnvResolutionWidth());
        }
        ;
        if (criteria.getCnvResolutionHeight() == null) {
            criteriaAux.add(in.getStreams().get(0).height);
        } else {
            criteriaAux.add(criteria.getCnvResolutionHeight());
        }
        ;


//        criteriaAux.add(in.getStreams().get(0).codec_name);
//        criteriaAux.add(in.getStreams().get(1).codec_name);
//        int fps = in.getStreams().get(0).avg_frame_rate.getProperNumerator();
//        float fpsFloat = fps;
//        fpsFloat = fpsFloat >= 1000 ? (fpsFloat / 1000) : fpsFloat;
//        criteriaAux.add(fpsFloat);
//        criteriaAux.add(in.getStreams().get(0).display_aspect_ratio);
//        criteriaAux.add(Integer.toString(in.getStreams().get(0).width));
//        criteriaAux.add(Integer.toString(in.getStreams().get(0).height));
//        for (Object o : criteriaAux) {
//            System.out.println(o);
//        }
        return criteriaAux;
    }

    private String getCodec(String cnvVideoType) {
        String format = null;
        if (cnvVideoType.equals("mkv")) {
            format = "matroska";
        } else {
            format = cnvVideoType;
        }
        return format;
    }

    private Fraction getFractionSelected(String cnvFps) {
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
        return f;
    }
}
