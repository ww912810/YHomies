package com.example.dbh.yhomies.mode;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.format.Formatter;

import com.example.dbh.yhomies.mode.Bean.AudioFileBean;
import com.example.dbh.yhomies.mode.m_interface.IGetAudioMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GetAudioMode {

    public static void getAudioFile(IGetAudioMode iGetAudioMode, Context mContext) {
        try {
            ArrayList<AudioFileBean> audioFileBeans = new ArrayList<>();
            ContentResolver resolver = mContext.getContentResolver();
            Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            Cursor cursor = resolver.query(musicUri, null, null, null, null);
            int id = 0;
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
                String display_name = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
                long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
                long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
                String sizeValue = Formatter.formatFileSize(mContext, Long.valueOf(size));
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
                String time = sdf.format(new Date(duration));
                id++;
                int idStr = id;
                AudioFileBean audioFileBean = new AudioFileBean();
                audioFileBean.fileId = idStr;
                audioFileBean.fileTitle = title;
                audioFileBean.fileDisplayName = display_name;
                audioFileBean.fileSinger = singer;
                audioFileBean.filePath = path;
                audioFileBean.fileTime = time;
                audioFileBean.fileStatus = false;
                audioFileBean.fileSize = sizeValue;
                audioFileBeans.add(audioFileBean);
            }
            iGetAudioMode.onSuccess(audioFileBeans);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
