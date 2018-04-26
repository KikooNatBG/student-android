package studentnetwork.android.com.studentnetwork.fragment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import studentnetwork.android.com.studentnetwork.R;
import studentnetwork.android.com.studentnetwork.bo.Content;
import studentnetwork.android.com.studentnetwork.fragment.CardItemFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Content} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCardItemRecyclerViewAdapter extends RecyclerView.Adapter<MyCardItemRecyclerViewAdapter.ViewHolder> {

    private final List<Content> mValues;
    private final OnListFragmentInteractionListener mListener;
    private Context context;


    public MyCardItemRecyclerViewAdapter(List<Content> items, OnListFragmentInteractionListener listener, Context context) {
        mValues = items;
        mListener = listener;
        this.context =context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
       // holder.userName.setText(mValues.get(position).get());
        holder.descriptionView.setText(mValues.get(position).getDescription());
        switch (mValues.get(position).getType()) {
            case INFO:
                break;
            case COLLOC:
                holder.serviceIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_action_find));
                break;
            case COVOIT:
                holder.serviceIcon.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_action_travel));
                break;
        }
       // holder.serviceIcon.setImageDrawable();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View cardView;
        public final TextView userName;
        public final ImageView profileImage;
        public final ImageView serviceIcon;
        public final TextView descriptionView;

        public Content mItem;

        public ViewHolder(View view) {
            super(view);
            cardView = view;

            profileImage = (ImageView) view.findViewById(R.id.serviceIcone);
            userName = (TextView) view.findViewById(R.id.serviceName);
            descriptionView = (TextView) view.findViewById(R.id.serviceDescription);
            serviceIcon = (ImageView) view.findViewById(R.id.serviceIcone);
        }

//        @Override
//        public String toString() {
//            return super.toString() + " '" + mContentView.getText() + "'";
//        }
    }
}
