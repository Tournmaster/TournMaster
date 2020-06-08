package cat.udl.tidic.amb.tournmaster;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class TournamentDiffCallBack extends DiffUtil.ItemCallback<Tournament> {

    @Override
    public boolean areItemsTheSame(@NonNull Tournament oldItem, @NonNull Tournament newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Tournament oldItem, @NonNull Tournament newItem) {
        return oldItem.equals(newItem);
    }
}
