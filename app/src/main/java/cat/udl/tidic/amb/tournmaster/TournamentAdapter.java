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
        Tournament current_tournament = (Tournament) getItem(position);
        holder.nameTourn.setText(current_tournament.getName());
        holder.status.setText(current_tournament.getStatus());
        holder.type.setText(current_tournament.getType());

        if(current_tournament.getType().equals("A")){
            holder.type.setText("Americana");
        }
        if(current_tournament.getType().equals("L")){
            holder.type.setText("Liga");
        }
        if(current_tournament.getType().equals("D")){
            holder.type.setText("Draft");
        }

        if(current_tournament.getStatus().equals("O")){
            holder.verdeOpen.setVisibility(View.VISIBLE);
            holder.status.setText("Abierto");
        }
        if(current_tournament.getStatus().equals("C")){
            holder.rojoPrivate.setVisibility(View.VISIBLE);
            holder.status.setText("Cerrado");
        }
        if(current_tournament.getStatus().equals("G")){
            holder.status.setText("Iniciado");
            holder.naranjaEmpez.setVisibility(View.VISIBLE);
        }

        //Log.d(TAG, "Photo URl:" + current_player.getPhoto());

        //Picasso.get().load(current_player.getPhoto()).into(holder.photo);
    }

    public Tournament getTournamentAt(int position){
        Log.d(TAG, "Position: "+ position);
        Log.d(TAG, "username: "+ getItem(position).getName());
        return getItem(position);
    }

    class TournamentHolder extends RecyclerView.ViewHolder {
        private TextView nameTourn;
        private TextView status;
        private TextView type;
        private TextView data;
        private ImageView verdeOpen;
        private ImageView rojoPrivate;
        private ImageView naranjaEmpez;


        public TournamentHolder(View itemView) {
            super(itemView);
              nameTourn = itemView.findViewById(R.id.text_nomTour);
              status = itemView.findViewById(R.id.text_estadoTour);
              type = itemView.findViewById(R.id.text_tipoTour);
              verdeOpen = itemView.findViewById(R.id.img_estadoVerd);
              rojoPrivate = itemView.findViewById(R.id.img_estadRoj);
              naranjaEmpez = itemView.findViewById(R.id.img_estadoEmpez);

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
        void onItemClick(Tournament tournmanet);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.eventItemListener = listener;
    }
}
