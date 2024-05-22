package com.nyamutsambira.footballliveupdates.Adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.nyamutsambira.footballliveupdates.ModelClasses.MatchEvents;
import com.nyamutsambira.footballliveupdates.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MatchEventsAdapter extends RecyclerView.Adapter<MatchEventsAdapter.ViewHolder> {

    private Context context;
    private List<MatchEvents> matchEventsList;

    public MatchEventsAdapter(Context context, List<MatchEvents> matchEventsList) {
        this.context = context;
        this.matchEventsList = matchEventsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.events_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        MatchEvents matchEvent = matchEventsList.get(i);
        setEventToTextView(holder, matchEvent);

    }

    private void setEventToTextView(ViewHolder holder, MatchEvents evt) {
        holder.timeTv.setText(evt.getTime() + "'");
        String team = evt.getHomeOrAway();
        if(team.equalsIgnoreCase("h"))
        {
            holder.homeTeamLl.setVisibility(View.VISIBLE);
            holder.awayTeamLl.setVisibility(View.GONE);

            if(evt.getEvent().trim().equalsIgnoreCase("own_goal"))
            {
                holder.homePlayerTv.setText(evt.getPlayer().trim() + " (OG)");
            }
            else if(evt.getEvent().trim().equalsIgnoreCase("penalty_goal"))
            {
                holder.homePlayerTv.setText(evt.getPlayer().trim() + " (P)");
            }
            else
            {
                holder.homePlayerTv.setText(evt.getPlayer().trim());
            }

            setEventLogoIv(holder.homeEventLogoIv, evt);
        }
        else
        {
            holder.awayTeamLl.setVisibility(View.VISIBLE);
            holder.homeTeamLl.setVisibility(View.GONE);
            if(evt.getEvent().trim().equalsIgnoreCase("own_goal"))
            {
                holder.awayPlayerTv.setText(evt.getPlayer().trim() + " (OG)");
            }
            else if(evt.getEvent().trim().equalsIgnoreCase("penalty_goal"))
            {
                holder.awayPlayerTv.setText(evt.getPlayer().trim() + " (P)");
            }
            else
            {
                holder.awayPlayerTv.setText(evt.getPlayer().trim());
            }

            setEventLogoIv(holder.awayEventLogoIv, evt);
        }
    }

    private void setEventLogoIv(ImageView iv, MatchEvents evt) {
        String eventType = evt.getEvent();
        ImageViewCompat.setImageTintList(iv, null);

        switch (eventType.toLowerCase().trim())
        {
            case "substitution":    Picasso.get().load(R.drawable.substitution).into(iv);
                                    break;
            case "penalty_goal":
            case "own_goal":
            case "goal":            Picasso.get().load(R.drawable.goal).into(iv);
                                    break;
            case "missed_penalty":  Picasso.get().load(R.drawable.missed).into(iv);
                                    break;
            case "yellow_card":     Picasso.get().load(R.drawable.cards).into(iv);
                                    int tintColor = ContextCompat.getColor(context, R.color.amber);
                                    ImageViewCompat.setImageTintList(iv, ColorStateList.valueOf(tintColor));
                                    break;
            case "yellow_red_card": Picasso.get().load(R.drawable.yy).into(iv);
                                    break;
            case "red_card":        Picasso.get().load(R.drawable.cards).into(iv);
                                    break;
            default:                break;
        }

    }

    @Override
    public int getItemCount() {
        return matchEventsList.size();
    }

    public void updateAdapter(List<MatchEvents> list){
        this.matchEventsList = list;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView timeTv,  homePlayerTv,  awayPlayerTv;
        private ImageView homeEventLogoIv, awayEventLogoIv;
        private LinearLayout homeTeamLl, awayTeamLl;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            timeTv = itemView.findViewById(R.id.events_layout_time);
            homePlayerTv = itemView.findViewById(R.id.events_layout_player_home_team);
            awayPlayerTv = itemView.findViewById(R.id.events_layout_player_away_team);
            homeEventLogoIv = itemView.findViewById(R.id.events_layout_event_logo_home_team);
            awayEventLogoIv = itemView.findViewById(R.id.events_layout_event_logo_away_team);
            homeTeamLl = itemView.findViewById(R.id.events_layout_home_team_events_Ll);
            awayTeamLl = itemView.findViewById(R.id.events_layout_away_team_events_Ll);

        }
    }
}
