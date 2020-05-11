package cat.udl.tidic.amb.tournmaster;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

class TournamentAdapter extends ListAdapter<Tournament, TournamentAdapter.TournamentHolder> {

    private final static String TAG = "UserAdapter";
    private OnItemClickListener eventItemListener;

    protected TournamentAdapter(@NonNull DiffUtil.ItemCallback<Tournament> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public TournamentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tournament_row_item, parent, false);
        return new TournamentHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull TournamentHolder holder, int position) {
        Tournament current_player = (Tournament) getItem(position);
        holder.nameTourn.setText(current_player.toString());


        //Log.d(TAG, "Photo URl:" + current_player.getPhoto());

        //Picasso.get().load(current_player.getPhoto()).into(holder.photo);
    }

    public Tournament getTournamentAt(int position){
        //Log.d(TAG, "Position: "+ position);
        //Log.d(TAG, "username: "+ getItem(position).getUsername());
        return getItem(position);
    }

    class TournamentHolder extends RecyclerView.ViewHolder {
        private TextView nameTourn;
        private TextView usergenere;
        private ImageView female;
        private ImageView male;
        private ImageView left;
        private ImageView rigth;
        private TextView golpe;
        private ImageView photo;


        public TournamentHolder(View itemView) {
            super(itemView);
              nameTourn = itemView.findViewById(R.id.nameTourn);
//            usernameTextView = itemView.findViewById(R.id.playerName);
              photo = itemView.findViewById(R.id.img_tourn);
//            photo = itemView.findViewById(R.id.photo);
//            female = itemView.findViewById(R.id.img_girl);
//            male = itemView.findViewById(R.id.img_boy);
//            left = itemView.findViewById(R.id.img_psIz);
//            rigth = itemView.findViewById(R.id.img_pscDere);
//            golpe = itemView.findViewById(R.id.text_golpuser);
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
        void onItemClick(Tournament tournmanet);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
