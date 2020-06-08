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

class MatchesAdapter extends ListAdapter<Match, MatchesAdapter.MatchesHolder> {

    private final static String TAG = "UserAdapter";
    private OnItemClickListener eventItemListener;

    protected MatchesAdapter(@NonNull DiffUtil.ItemCallback<Match> diffCallback) {
        super(diffCallback);
    }


    @NonNull
    @Override
    public MatchesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tournament_draw_item, parent, false);
        return new MatchesHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull MatchesHolder holder, int position) {
        Match current_matches = (Match) getItem(position);

        //holder.nameTourn.setText(current_rounds.getName());
        holder.player1.setText(current_matches.getCouple1Player1_id().getName()+current_matches.getCouple1Player1_id().getSurname());
        holder.player2.setText(current_matches.getCouple1Player2_id().getName()+current_matches.getCouple1Player2_id().getSurname());
        holder.player3.setText(current_matches.getCouple2Player1_id().getName()+current_matches.getCouple2Player1_id().getSurname());
        holder.player4.setText(current_matches.getCouple2Player2_id().getName()+current_matches.getCouple2Player2_id().getSurname());
        String [] aux_sets= current_matches.getSet1().split("/");
        holder.set1.setText(aux_sets[0]);
        holder.set4.setText(aux_sets[1]);
        aux_sets= current_matches.getSet2().split("/");
        holder.set2.setText(aux_sets[0]);
        holder.set5.setText(aux_sets[1]);
        aux_sets= current_matches.getSet3().split("/");
        holder.set3.setText(aux_sets[0]);
        holder.set6.setText(aux_sets[1]);
        //Log.d(TAG, "Photo URl:" + current_player.getPhoto());

        //Picasso.get().load(current_player.getPhoto()).into(holder.photo);
    }

    public Match getRoundsAt(int position){
        Log.d(TAG, "Position: "+ position);
        Log.d(TAG, "username: "+ getItem(position));
        return getItem(position);
    }

    class MatchesHolder extends RecyclerView.ViewHolder {
        private TextView player1;
        private TextView player2;
        private TextView player3;
        private TextView player4;
        private TextView set1;
        private TextView set2;
        private TextView set3;
        private TextView set4;
        private TextView set5;
        private TextView set6;



        public MatchesHolder(View itemView) {
            super(itemView);
            player1 = itemView.findViewById(R.id.text_player1);
            player2 = itemView.findViewById(R.id.text_player2);
            player3 = itemView.findViewById(R.id.text_player3);
            player4 = itemView.findViewById(R.id.text_player4);
            set1 = itemView.findViewById(R.id.text_set1);
            set2 = itemView.findViewById(R.id.text_set2);
            set3 = itemView.findViewById(R.id.text_set3);
            set4 = itemView.findViewById(R.id.text_set4);
            set5 = itemView.findViewById(R.id.text_set5);
            set6 = itemView.findViewById(R.id.text_set6);

//            usernameTextView = itemView.findViewById(R.id.playerName);
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
        void onItemClick(Match matches);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
