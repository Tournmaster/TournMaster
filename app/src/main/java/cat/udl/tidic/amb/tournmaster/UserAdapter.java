package cat.udl.tidic.amb.tournmaster;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        if(current_player.getGenere().equals("M")){
            Log.d("TAG","ENTRA");
            holder.male.setVisibility(View.VISIBLE);
        }
        else{
            holder.female.setVisibility(View.VISIBLE);
        }
        if(current_player.getPosicion().equals("L")){
            Log.d("TAG","ENTRA");
            holder.left.setVisibility(View.VISIBLE);
        }
        else{
            holder.rigth.setVisibility(View.VISIBLE);
        }
        if(current_player.getPrefSmash().equals("S")){
            holder.golpe.setText("Saque");
        }
        if(current_player.getPrefSmash().equals("R")){
            holder.golpe.setText("Right");
        }
        if(current_player.getPrefSmash().equals("L")){
            holder.golpe.setText("Reves");
        }
        if(current_player.getPrefSmash().equals("G")){
            holder.golpe.setText("Globo");
        }
        if(current_player.getPrefSmash().equals("C")){
            holder.golpe.setText("Cortada");
        }
        if(current_player.getPrefSmash().equals("M")){
            holder.golpe.setText("Smash");
        }
        if(current_player.getPrefSmash().equals("V")){
            holder.golpe.setText("Volea");
        }

        Log.d(TAG, "Photo URl:" + current_player.getPhoto());

        Picasso.get().load(current_player.getPhoto()).into(holder.photo);
    }

    public User getUserAt(int position){
        Log.d(TAG, "Position: "+ position);
        Log.d(TAG, "username: "+ getItem(position).getUsername());
        return getItem(position);
    }

    class UserHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;
        private TextView usergenere;
        private ImageView female;
        private ImageView male;
        private ImageView left;
        private ImageView rigth;
        private TextView golpe;
        private ImageView photo;

        public UserHolder(View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.playerName);
            photo = itemView.findViewById(R.id.photo);
            female = itemView.findViewById(R.id.img_girl);
            male = itemView.findViewById(R.id.img_boy);
            left = itemView.findViewById(R.id.img_psIz);
            rigth = itemView.findViewById(R.id.img_pscDere);
            golpe = itemView.findViewById(R.id.text_golpuser);
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
