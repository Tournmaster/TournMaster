package cat.udl.tidic.amb.tournmaster;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class MatchesDiffCallBack extends DiffUtil.ItemCallback<Match> {

    @Override
    public boolean areItemsTheSame(@NonNull Match oldItem, @NonNull Match newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Match oldItem, @NonNull Match newItem) {
        return oldItem.equals(newItem);
    }
}
