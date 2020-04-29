package cat.udl.tidic.amb.tournmaster;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

class UserAdapter extends ListAdapter<User, UserAdapter.UserHolder> {

    private final static String TAG = "UserAdapter";
    private OnItemClickListener eventItemListener;

    protected UserAdapter(@NonNull DiffUtil.ItemCallback<User> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_row_item, parent, false);
        return new UserHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User current_player = (User) getItem(position);
        holder.usernameTextView.setText(current_player.toString());
    }

    public User getUserAt(int position){
        Log.d(TAG, "Position: "+ position);
        Log.d(TAG, "username: "+ getItem(position).getUsername());
        return getItem(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;

        public UserHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.playerName);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (eventItemListener != null && position != RecyclerView.NO_POSITION) {
                        eventItemListener.onItemClick(getItem(position));
                    }
                }
            });
        }

    }
    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
