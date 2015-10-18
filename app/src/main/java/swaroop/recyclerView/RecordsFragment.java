package swaroop.recyclerView;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import swaroop.recyclerView.db.FormData;
import swaroop.recyclerView.db.dao.FormDataDao;

public class RecordsFragment extends Fragment {
    private final Logger mLog = LoggerFactory.getLogger(getClass());
    private RecordsViewAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.records_fragment, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recordsfrag_scrollableview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        mAdapter = new RecordsViewAdapter(fetchRecords());
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    public void onRecordsChanged() {
        mLog.trace("onRecordsChanged()");
        if (mAdapter != null) {
            mAdapter.updateData(fetchRecords());
        } else {
            mLog.debug("RecyclerView adapter is not set");
        }
    }

    private List<FormData> fetchRecords() {
        FormDataDao dao = new FormDataDao(getActivity());
        return (dao != null) ? dao.listAll() : new ArrayList<FormData>();
    }
}
