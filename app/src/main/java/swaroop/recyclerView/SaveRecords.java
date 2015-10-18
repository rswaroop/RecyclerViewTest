package swaroop.recyclerView;

import android.content.Context;
import android.webkit.JavascriptInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swaroop.recyclerView.db.FormData;
import swaroop.recyclerView.db.dao.FormDataDao;

public class SaveRecords {
    private final Logger mLog = LoggerFactory.getLogger(getClass());
    private final Context mContext;
    private final Callback mCallback;

    public SaveRecords(Context context, Callback callback) {
        mContext = context;
        mCallback = callback;
    }

    @JavascriptInterface
    public void saveData(String jsonData) {
        try {
            JSONObject obj = new JSONObject(jsonData);
            JSONArray jsonArray = obj.getJSONArray("array");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String value = jsonObject.getString("value");
                FormData data = new FormData();
                data.setName(name);
                data.setValue(value);
                FormDataDao dao = new FormDataDao(mContext);
                dao.insert(data);
            }
            if (mCallback != null) {
                mCallback.onDataSaved();
            } else {
                mLog.debug("Callback is not set.");
            }
        } catch (JSONException e) {
            mLog.error("Error while saving to database : ", e);
        }
    }

    public interface Callback {
        void onDataSaved();
    }
}
