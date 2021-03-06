package app.complyglobal.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.complyglobal.dummy.DummyContent;
import app.complyglobal.fragment.DueTodayFragment.OnListFragmentInteractionListener;
import app.complyglobal.R;
import app.complyglobal.dummy.DummyContent.DummyItem;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class ListViewAdapterForDueToday extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public ListViewAdapterForDueToday(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("MyItemRecycl","onCreateViewHolder +"+mValues.size());
        View view;
        if(viewType==TYPE_HEADER){
            Log.i("MyItemRecycl","Header added");
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.due_today_header_file, parent, false);
            return new HeaderHolder(view);
        }else   if(viewType==TYPE_ITEM){
            Log.i("MyItemRecycl","Items added");

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
            return new ListViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView. ViewHolder holder, int position) {
        Log.i("MyItemRecycl","onBindViewHolder");
        if(holder instanceof  HeaderHolder){
            Log.i("MyItemRecycl","onBindViewHolder HeaderHolder added");
            HeaderHolder header= (HeaderHolder) holder;
        }else if(holder instanceof  ListViewHolder){
            Log.i("MyItemRecycl","onBindViewHolder ViewHolder added");
            ListViewHolder contentView= (ListViewHolder) holder;
            contentView.mItem = mValues.get(position);
            contentView.mIdView.setText(mValues.get(position).id);
            contentView.mContentView.setText(mValues.get(position).content);

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
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position > mValues.size();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                // Create a FilterResults object
                FilterResults results = new FilterResults();

                // If the constraint (search string/pattern) is null
                // or its length is 0, i.e., its empty then
                // we just set the `values` property to the
                // original Compliance list which contains all of them
                if (constraint == null || constraint.length() == 0) {
                    results.values = mValues;
                    results.count = mValues.size();
                } else {
                    // Some search copnstraint has been passed
                    // so let's filter accordingly
                    ArrayList<DummyItem> filteredCompliance = new ArrayList<>();

                    // We'll go through all the Compliance and see
                    // if they contain the supplied string
                    for (DummyContent.DummyItem c : mValues) {
                        if (c.content.toUpperCase().contains(constraint.toString().toUpperCase())) {
                            // if `contains` == true then add it
                            // to our filtered list
                            filteredCompliance.add(c);
                        }
                    }

                    // Finally set the filtered values and size/count
                    results.values = filteredCompliance;
                    results.count = filteredCompliance.size();
                }

                // Return our FilterResults object
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mValues = (ArrayList<DummyItem>) results.values;
            }
        };
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public DummyItem mItem;

        public ListViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

    public class HeaderHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public HeaderHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
