package com.example.dbh.yhomies.view.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.dbh.yhomies.R;
import com.example.dbh.yhomies.mode.Bean.AudioFileBean;

import de.hdodenhof.circleimageview.CircleImageView;

public class AudioFileAdapter extends BaseQuickAdapter<AudioFileBean, BaseViewHolder> {

    private Context mContext;

    public AudioFileAdapter(Context mContext) {
        super(R.layout.item_audio_file_layout);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, AudioFileBean item) {
        helper.setText(R.id.tvFileName, item.fileTitle);
        helper.setText(R.id.tvFileTime, item.fileTime);
        helper.setText(R.id.tvFileSize, item.fileSize);
        //Glide.with(mContext).load(R.mipmap.ic_voice_file).crossFade().into((ImageView) helper.getView(R.id.ivFileLogo));
        helper.addOnClickListener(R.id.tvChoose);
        if (!item.fileStatus) {
            helper.setGone(R.id.skAudio, false);
        } else {
            helper.setVisible(R.id.skAudio, true);
        }
    }
}
