package com.cephmonitor.cephmonitor.model.logic.ceph.condition.notification;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import com.cephmonitor.cephmonitor.R;
import com.cephmonitor.cephmonitor.model.logic.ConditionNotification;
import com.resourcelibrary.model.network.api.ceph.object.ClusterV1Space;

import org.json.JSONException;

/**
 * Created by User on 5/13/2015.
 */
public class UsagePercentErrorNotification extends ConditionNotification<ClusterV1Space> {
    public static final float WARN_PERCENT_MAX = 0.8f;

    public UsagePercentErrorNotification(Context context) {
        super(context);
    }

    @Override
    protected boolean decide(ClusterV1Space data) {
        try {
            double percent = (double) data.getUsedBytes() / (double) data.getCapacityBytes();
            return percent >= WARN_PERCENT_MAX;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected Notification onTrue(ClusterV1Space data) {
        String title = getContext().getResources().getString(R.string.check_service_usage_percent_error_title);
        String content = getContext().getResources().getString(R.string.check_service_usage_percent_error_content);

        Notification msg = new NotificationCompat.Builder(getContext())
                .setContentIntent(null)
                .setTicker(content)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(title)
                .setContentText(content)
                .build();
        return msg;
    }
}
