package app.complyglobal.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.complyglobal.dto.ComplianceDTO;
import app.complyglobal.dto.EventItem;
import app.complyglobal.dto.HeaderItem;
import app.complyglobal.dto.ListItem;
import app.complyglobal.fragment.CheckedListFragment.OnListFragmentInteractionListener;
import app.complyglobal.R;
import app.complyglobal.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListViewAdapterForChecklist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final List<ListItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListViewAdapterForChecklist(List<ListItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("MyItemRecycl","onCreateViewHolder +"+mValues.size());
        View view;
        if(viewType==TYPE_HEADER){
            Log.i("MyItemRecycl","Header added");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_header_file, parent, false);
            return new HeaderHolder(view);
        }else   if(viewType==TYPE_ITEM){
            Log.i("MyItemRecycl","Items added");

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ListViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        Log.i("MyItemRecycl","onBindViewHolder");
        if(holder instanceof  HeaderHolder){
            Log.i("MyItemRecycl","onBindViewHolder HeaderHolder added");
            HeaderHolder header= (HeaderHolder) holder;
            HeaderItem headerItem=(HeaderItem)mValues.get(position);
            header.mView.setText(headerItem.getHeaderItem());
        }else if(holder instanceof  ListViewHolder){
            Log.i("MyItemRecycl","onBindViewHolder ViewHolder added");
            ListViewHolder contentView= (ListViewHolder) holder;
            EventItem eventItem=(EventItem)mValues.get(position);
            contentView.mIdView.setText("456");
            //contentView.dueOn.setText(eventItem.getEventItem().getDueDateString());
            contentView.mContentView.setText(eventItem.getEventItem().getComplianceTaskname());

            contentView.status.setText(eventItem.getEventItem().getStatus());
            contentView.complianceType.setText(eventItem.getEventItem().getComplianceTypeName());
            contentView.complianceCategory.setText(eventItem.getEventItem().getComplianceCaetgoryName());
            if(eventItem.getEventItem().isWorkFlow())
                contentView.workflow.setVisibility(View.VISIBLE);

            contentView.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(((ListViewHolder) holder).mItem);
                    }
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        Log.i("MyItemRecycl","getItemViewType positon : +"+position);
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        }

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return mValues.get(position).getType()==0;
    }

    private boolean isPositionFooter(int position) {
        return position > mValues.size();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView workflow;
        public ComplianceDTO mItem;
        public final TextView dueOn;
        public final TextView status;
        public final TextView complianceType;
        public final TextView complianceCategory;
        public ListViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            workflow=(ImageView)view.findViewById(R.id.workflow);
            dueOn=(TextView) view.findViewById(R.id.due_one);
            status=(TextView) view.findViewById(R.id.status_text);
            complianceType=(TextView) view.findViewById(R.id.compliance_type);
            complianceCategory=(TextView) view.findViewById(R.id.compliance_category);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public final TextView mView;

        public HeaderHolder(View view) {
            super(view);
            mView = (TextView)view.findViewById(R.id.header_id);
        }
    }
}
