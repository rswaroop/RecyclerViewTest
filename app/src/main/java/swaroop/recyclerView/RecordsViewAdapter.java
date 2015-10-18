package swaroop.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import swaroop.recyclerView.db.FormData;
import swaroop.recyclerView.utils.Common;

public class RecordsViewAdapter extends RecyclerView.Adapter<RecordsViewHolder> {
    private final Logger mLog = LoggerFactory.getLogger(getClass());
    private List<FormData> mDatabaseData;

    public RecordsViewAdapter(final List<FormData> data) {
        mLog.trace("ctor({})", data);
        mDatabaseData = new ArrayList<>(data);
        updateData(data);
    }

    @Override
    public RecordsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerlist_item, viewGroup, false);
        RecordsViewHolder viewHolder = new RecordsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecordsViewHolder versionViewHolder, int i) {
        FormData data = getItem(i);
        versionViewHolder.name.setText(data.getName());
        versionViewHolder.value.setText(data.getValue());
    }

    @Override
    public int getItemCount() {
        return (mDatabaseData != null) ? mDatabaseData.size() : 0;
    }

    public FormData getItem(int position) {
        return (mDatabaseData != null) ? mDatabaseData.get(position) : null;
    }

    public void updateData(List<FormData> data) {
        mLog.trace("updateData({})", data);
        mDatabaseData.clear();
        mDatabaseData = new ArrayList<>(data);
        Common.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                notifyDataSetChanged();
            }
        });
    }
}