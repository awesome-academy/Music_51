package com.framgia.music_51.screen.home;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.framgia.music_51.R;
import com.framgia.music_51.data.model.Track;
import com.framgia.music_51.databinding.ItemTrackBinding;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {
    private List<Track> mTracks;
    private Context mContext;

    public TrackAdapter(Context context) {
        mTracks = new ArrayList<>();
        mContext = context;
    }

    public void setData(List<Track> tracks) {
        if (tracks == null) {
            return;
        }
        mTracks.addAll(tracks);
        int start = mTracks.size();
        notifyItemRangeChanged(start, tracks.size());
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemTrackBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()), R.layout.item_track, viewGroup, false);
        return new TrackViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder trackViewHolder, int i) {
        trackViewHolder.setBinding(mContext, mTracks.get(i));
    }

    @Override
    public int getItemCount() {
        return mTracks != null ? mTracks.size() : 0;
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private ItemTrackBinding mBinding;

        public TrackViewHolder(ItemTrackBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void setBinding(Context context, Track track) {
            mBinding.setHandlerClick(new HandlerClick(context, mTracks));
            mBinding.setTrack(track);
            mBinding.executePendingBindings();
        }
    }
}
