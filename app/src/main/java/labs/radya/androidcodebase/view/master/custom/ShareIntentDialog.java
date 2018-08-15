package labs.radya.androidcodebase.view.master.custom;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import labs.radya.androidcodebase.R;

public class ShareIntentDialog extends DialogFragment {
    private Context context;
    private String content;

    public void setSubjectAndContent(Context context, String content) {
        this.content = content;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle(R.string.labelTitleShare);
        View view = inflater.inflate(R.layout.share_intent_dialog, container, false);
        ListView listView = view.findViewById(R.id.listview);

        Intent sendIntent = new Intent(android.content.Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        Context context = getActivity();
        List activities = context.getPackageManager().queryIntentActivities(sendIntent, 0);
        final ShareAdapter adapter = new ShareAdapter(activities.toArray(), inflater, context);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                FragmentActivity context = getActivity();
                if (context != null) {
                    ResolveInfo info = (ResolveInfo) adapter.getItem(arg2);
                    Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
                    intent.setType("text/plain");

                    String shareChannel = info.activityInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    intent.putExtra(Intent.EXTRA_TEXT, content);
                    context.startActivity(intent);

                    getDialog().dismiss();
                }

            }
        });
        return view;
    }

    public class ShareAdapter extends BaseAdapter {
        Object[] items;
        private LayoutInflater mInflater;
        Context context;

        public ShareAdapter(Object[] items, LayoutInflater inflater, Context context) {
            this.items = items;
            mInflater = inflater;
            this.context = context;
        }

        public int getCount() {
            return items.length;
        }

        public Object getItem(int position) {
            return items[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.share_intent_item, null);
                holder = new ViewHolder();
                holder.name = convertView.findViewById(R.id.shareName);
                holder.logo = convertView.findViewById(R.id.shareImage);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            ResolveInfo item = (ResolveInfo) items[position];

            String name = item.activityInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            holder.name.setText(name);

            holder.logo.setImageDrawable(item.activityInfo.applicationInfo
                    .loadIcon(context.getPackageManager()));

            return convertView;
        }

        class ViewHolder {

            TextView name;
            ImageView logo;
        }
    }
}
