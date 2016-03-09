package dillonbast.hockeyquestionmark.hqm;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Dillon on 2016-02-08.
 */
public class RSLRosterFragment extends Fragment {
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.rsl_ros_fragment, container, false);
        return view;


    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new JsoupRun().execute();
        //CODE GOES HERE
    }

    private class JsoupRun extends AsyncTask<Void, Void, Void> {
        String url = "https://sites.google.com/site/hockeyquestionmarkapp/RSLRoster";
        Elements td;
        Elements tr;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("RSL Rosters");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Get all the TDS
                td = document.getElementsByTag("td");
                tr = document.getElementsByTag("tr");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            //team
            TextView team1_name = (TextView) getView().findViewById(R.id.tv_rsl_team_1_roster);
            TextView team1_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_gp);
            TextView team1_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_wins);
            TextView team1_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_OTW);
            TextView team1_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_OTL);
            TextView team1_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_LOSSES);
            TextView team1_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_POINTS);
            TextView team1_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_1_stats_STREAK);

            //players
            String t1_player_name="";
            String t1_player_pos="";
            String t1_player_role="";
            String t1_player_points="";
            String t1_player_goals="";
            String t1_player_assis="";
            String t1_player_ppg="";
            String t1_player_plmi="";
            String t1_player_gwg="";
            String t1_player_gperc="";
            String t1_player_saves="";
            String t1_player_saveperc="";
            String t1_player_ga="";
            String t1_player_gaa="";
            String t1_player_gp="";
            String t1_player_gpag="";
            String t1_player_wins="";
            String t1_player_so="";

            TextView team1_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_name);
            TextView team1_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_pos);
            TextView team1_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_role);
            TextView team1_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_pts);
            TextView team1_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_goals);
            TextView team1_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_assists);
            TextView team1_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_ppg);
            TextView team1_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_plusmin);
            TextView team1_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_gwg);
            TextView team1_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_goalperc);
            TextView team1_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_saves);
            TextView team1_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_saveperc);
            TextView team1_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_goalsagainst);
            TextView team1_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_goalsagainstavg);
            TextView team1_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_gamesplayed);
            TextView team1_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_gamesplayedasg);
            TextView team1_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_wins);
            TextView team1_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_1_player_stats_shutouts);
            int trcount = 0;
            int tdcount = 0;
            int teamCount = 0;
            int rosterCount = 0;
            int maxPlayer = 0;
            boolean teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= 6)
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t1_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t1_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t1_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t1_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t1_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t1_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t1_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t1_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t1_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t1_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t1_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t1_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t1_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t1_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t1_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t1_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t1_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t1_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team1_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team1_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team1_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team1_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team1_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team1_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team1_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team1_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team1_player_name.setText(t1_player_name);
            team1_player_pos.setText(t1_player_pos);
            team1_player_role.setText(t1_player_role);
            team1_player_points.setText(t1_player_points);
            team1_player_goals.setText(t1_player_goals);
            team1_player_assis.setText(t1_player_assis);
            team1_player_ppg.setText(t1_player_ppg);
            team1_player_plmi.setText(t1_player_plmi);
            team1_player_gwg.setText(t1_player_gwg);
            team1_player_gperc.setText(t1_player_gperc);
            team1_player_saves.setText(t1_player_saves);
            team1_player_saveperc.setText(t1_player_saveperc);
            team1_player_ga.setText(t1_player_ga);
            team1_player_gaa.setText(t1_player_gaa);
            team1_player_gp.setText(t1_player_gp);
            team1_player_gpag.setText(t1_player_gpag);
            team1_player_wins.setText(t1_player_wins);
            team1_player_so.setText(t1_player_so);

            //team
            TextView team2_name = (TextView) getView().findViewById(R.id.tv_rsl_team_2_roster);
            TextView team2_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_gp);
            TextView team2_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_wins);
            TextView team2_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_OTW);
            TextView team2_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_OTL);
            TextView team2_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_LOSSES);
            TextView team2_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_POINTS);
            TextView team2_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_2_stats_STREAK);

//players
            String t2_player_name="";
            String t2_player_pos="";
            String t2_player_role="";
            String t2_player_points="";
            String t2_player_goals="";
            String t2_player_assis="";
            String t2_player_ppg="";
            String t2_player_plmi="";
            String t2_player_gwg="";
            String t2_player_gperc="";
            String t2_player_saves="";
            String t2_player_saveperc="";
            String t2_player_ga="";
            String t2_player_gaa="";
            String t2_player_gp="";
            String t2_player_gpag="";
            String t2_player_wins="";
            String t2_player_so="";

            TextView team2_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_name);
            TextView team2_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_pos);
            TextView team2_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_role);
            TextView team2_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_pts);
            TextView team2_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_goals);
            TextView team2_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_assists);
            TextView team2_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_ppg);
            TextView team2_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_plusmin);
            TextView team2_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_gwg);
            TextView team2_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_goalperc);
            TextView team2_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_saves);
            TextView team2_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_saveperc);
            TextView team2_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_goalsagainst);
            TextView team2_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_goalsagainstavg);
            TextView team2_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_gamesplayed);
            TextView team2_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_gamesplayedasg);
            TextView team2_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_wins);
            TextView team2_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_2_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + 160))
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t2_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t2_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t2_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t2_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t2_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t2_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t2_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t2_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t2_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t2_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t2_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t2_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t2_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t2_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t2_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t2_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t2_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t2_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team2_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team2_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team2_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team2_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team2_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team2_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team2_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team2_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team2_player_name.setText(t2_player_name);
            team2_player_pos.setText(t2_player_pos);
            team2_player_role.setText(t2_player_role);
            team2_player_points.setText(t2_player_points);
            team2_player_goals.setText(t2_player_goals);
            team2_player_assis.setText(t2_player_assis);
            team2_player_ppg.setText(t2_player_ppg);
            team2_player_plmi.setText(t2_player_plmi);
            team2_player_gwg.setText(t2_player_gwg);
            team2_player_gperc.setText(t2_player_gperc);
            team2_player_saves.setText(t2_player_saves);
            team2_player_saveperc.setText(t2_player_saveperc);
            team2_player_ga.setText(t2_player_ga);
            team2_player_gaa.setText(t2_player_gaa);
            team2_player_gp.setText(t2_player_gp);
            team2_player_gpag.setText(t2_player_gpag);
            team2_player_wins.setText(t2_player_wins);
            team2_player_so.setText(t2_player_so);

            //team
            TextView team3_name = (TextView) getView().findViewById(R.id.tv_rsl_team_3_roster);
            TextView team3_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_gp);
            TextView team3_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_wins);
            TextView team3_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_OTW);
            TextView team3_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_OTL);
            TextView team3_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_LOSSES);
            TextView team3_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_POINTS);
            TextView team3_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_3_stats_STREAK);

//players
            String t3_player_name="";
            String t3_player_pos="";
            String t3_player_role="";
            String t3_player_points="";
            String t3_player_goals="";
            String t3_player_assis="";
            String t3_player_ppg="";
            String t3_player_plmi="";
            String t3_player_gwg="";
            String t3_player_gperc="";
            String t3_player_saves="";
            String t3_player_saveperc="";
            String t3_player_ga="";
            String t3_player_gaa="";
            String t3_player_gp="";
            String t3_player_gpag="";
            String t3_player_wins="";
            String t3_player_so="";

            TextView team3_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_name);
            TextView team3_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_pos);
            TextView team3_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_role);
            TextView team3_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_pts);
            TextView team3_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_goals);
            TextView team3_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_assists);
            TextView team3_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_ppg);
            TextView team3_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_plusmin);
            TextView team3_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_gwg);
            TextView team3_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_goalperc);
            TextView team3_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_saves);
            TextView team3_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_saveperc);
            TextView team3_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_goalsagainst);
            TextView team3_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_goalsagainstavg);
            TextView team3_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_gamesplayed);
            TextView team3_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_gamesplayedasg);
            TextView team3_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_wins);
            TextView team3_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_3_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + 160 + 160))//when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t3_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t3_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t3_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t3_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t3_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t3_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t3_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t3_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t3_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t3_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t3_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t3_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t3_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t3_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t3_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t3_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t3_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t3_player_so += ele.html() + "\n";
                                        break;
                                    case 19:
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team3_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team3_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team3_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team3_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team3_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team3_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team3_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team3_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team3_player_name.setText(t3_player_name);
            team3_player_pos.setText(t3_player_pos);
            team3_player_role.setText(t3_player_role);
            team3_player_points.setText(t3_player_points);
            team3_player_goals.setText(t3_player_goals);
            team3_player_assis.setText(t3_player_assis);
            team3_player_ppg.setText(t3_player_ppg);
            team3_player_plmi.setText(t3_player_plmi);
            team3_player_gwg.setText(t3_player_gwg);
            team3_player_gperc.setText(t3_player_gperc);
            team3_player_saves.setText(t3_player_saves);
            team3_player_saveperc.setText(t3_player_saveperc);
            team3_player_ga.setText(t3_player_ga);
            team3_player_gaa.setText(t3_player_gaa);
            team3_player_gp.setText(t3_player_gp);
            team3_player_gpag.setText(t3_player_gpag);
            team3_player_wins.setText(t3_player_wins);
            team3_player_so.setText(t3_player_so);

            //team
            TextView team4_name = (TextView) getView().findViewById(R.id.tv_rsl_team_4_roster);
            TextView team4_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_gp);
            TextView team4_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_wins);
            TextView team4_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_OTW);
            TextView team4_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_OTL);
            TextView team4_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_LOSSES);
            TextView team4_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_POINTS);
            TextView team4_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_4_stats_STREAK);

//players
            String t4_player_name="";
            String t4_player_pos="";
            String t4_player_role="";
            String t4_player_points="";
            String t4_player_goals="";
            String t4_player_assis="";
            String t4_player_ppg="";
            String t4_player_plmi="";
            String t4_player_gwg="";
            String t4_player_gperc="";
            String t4_player_saves="";
            String t4_player_saveperc="";
            String t4_player_ga="";
            String t4_player_gaa="";
            String t4_player_gp="";
            String t4_player_gpag="";
            String t4_player_wins="";
            String t4_player_so="";

            TextView team4_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_name);
            TextView team4_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_pos);
            TextView team4_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_role);
            TextView team4_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_pts);
            TextView team4_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_goals);
            TextView team4_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_assists);
            TextView team4_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_ppg);
            TextView team4_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_plusmin);
            TextView team4_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_gwg);
            TextView team4_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_goalperc);
            TextView team4_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_saves);
            TextView team4_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_saveperc);
            TextView team4_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_goalsagainst);
            TextView team4_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_goalsagainstavg);
            TextView team4_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_gamesplayed);
            TextView team4_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_gamesplayedasg);
            TextView team4_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_wins);
            TextView team4_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_4_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + (160*3)))//when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t4_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t4_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t4_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t4_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t4_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t4_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t4_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t4_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t4_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t4_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t4_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t4_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t4_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t4_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t4_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t4_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t4_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t4_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team4_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team4_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team4_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team4_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team4_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team4_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team4_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team4_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team4_player_name.setText(t4_player_name);
            team4_player_pos.setText(t4_player_pos);
            team4_player_role.setText(t4_player_role);
            team4_player_points.setText(t4_player_points);
            team4_player_goals.setText(t4_player_goals);
            team4_player_assis.setText(t4_player_assis);
            team4_player_ppg.setText(t4_player_ppg);
            team4_player_plmi.setText(t4_player_plmi);
            team4_player_gwg.setText(t4_player_gwg);
            team4_player_gperc.setText(t4_player_gperc);
            team4_player_saves.setText(t4_player_saves);
            team4_player_saveperc.setText(t4_player_saveperc);
            team4_player_ga.setText(t4_player_ga);
            team4_player_gaa.setText(t4_player_gaa);
            team4_player_gp.setText(t4_player_gp);
            team4_player_gpag.setText(t4_player_gpag);
            team4_player_wins.setText(t4_player_wins);
            team4_player_so.setText(t4_player_so);

            //team
            TextView team5_name = (TextView) getView().findViewById(R.id.tv_rsl_team_5_roster);
            TextView team5_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_gp);
            TextView team5_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_wins);
            TextView team5_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_OTW);
            TextView team5_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_OTL);
            TextView team5_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_LOSSES);
            TextView team5_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_POINTS);
            TextView team5_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_5_stats_STREAK);

//players
            String t5_player_name="";
            String t5_player_pos="";
            String t5_player_role="";
            String t5_player_points="";
            String t5_player_goals="";
            String t5_player_assis="";
            String t5_player_ppg="";
            String t5_player_plmi="";
            String t5_player_gwg="";
            String t5_player_gperc="";
            String t5_player_saves="";
            String t5_player_saveperc="";
            String t5_player_ga="";
            String t5_player_gaa="";
            String t5_player_gp="";
            String t5_player_gpag="";
            String t5_player_wins="";
            String t5_player_so="";

            TextView team5_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_name);
            TextView team5_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_pos);
            TextView team5_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_role);
            TextView team5_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_pts);
            TextView team5_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_goals);
            TextView team5_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_assists);
            TextView team5_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_ppg);
            TextView team5_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_plusmin);
            TextView team5_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_gwg);
            TextView team5_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_goalperc);
            TextView team5_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_saves);
            TextView team5_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_saveperc);
            TextView team5_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_goalsagainst);
            TextView team5_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_goalsagainstavg);
            TextView team5_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_gamesplayed);
            TextView team5_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_gamesplayedasg);
            TextView team5_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_wins);
            TextView team5_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_5_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + (160*4)))//when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t5_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t5_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t5_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t5_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t5_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t5_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t5_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t5_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t5_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t5_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t5_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t5_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t5_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t5_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t5_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t5_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t5_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t5_player_so += ele.html() + "\n";
                                        break;
                                    case 19:
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team5_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team5_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team5_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team5_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team5_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team5_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team5_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team5_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team5_player_name.setText(t5_player_name);
            team5_player_pos.setText(t5_player_pos);
            team5_player_role.setText(t5_player_role);
            team5_player_points.setText(t5_player_points);
            team5_player_goals.setText(t5_player_goals);
            team5_player_assis.setText(t5_player_assis);
            team5_player_ppg.setText(t5_player_ppg);
            team5_player_plmi.setText(t5_player_plmi);
            team5_player_gwg.setText(t5_player_gwg);
            team5_player_gperc.setText(t5_player_gperc);
            team5_player_saves.setText(t5_player_saves);
            team5_player_saveperc.setText(t5_player_saveperc);
            team5_player_ga.setText(t5_player_ga);
            team5_player_gaa.setText(t5_player_gaa);
            team5_player_gp.setText(t5_player_gp);
            team5_player_gpag.setText(t5_player_gpag);
            team5_player_wins.setText(t5_player_wins);
            team5_player_so.setText(t5_player_so);
            //team
            TextView team6_name = (TextView) getView().findViewById(R.id.tv_rsl_team_6_roster);
            TextView team6_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_gp);
            TextView team6_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_wins);
            TextView team6_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_OTW);
            TextView team6_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_OTL);
            TextView team6_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_LOSSES);
            TextView team6_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_POINTS);
            TextView team6_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_6_stats_STREAK);

//players
            String t6_player_name="";
            String t6_player_pos="";
            String t6_player_role="";
            String t6_player_points="";
            String t6_player_goals="";
            String t6_player_assis="";
            String t6_player_ppg="";
            String t6_player_plmi="";
            String t6_player_gwg="";
            String t6_player_gperc="";
            String t6_player_saves="";
            String t6_player_saveperc="";
            String t6_player_ga="";
            String t6_player_gaa="";
            String t6_player_gp="";
            String t6_player_gpag="";
            String t6_player_wins="";
            String t6_player_so="";

            TextView team6_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_name);
            TextView team6_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_pos);
            TextView team6_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_role);
            TextView team6_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_pts);
            TextView team6_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_goals);
            TextView team6_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_assists);
            TextView team6_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_ppg);
            TextView team6_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_plusmin);
            TextView team6_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_gwg);
            TextView team6_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_goalperc);
            TextView team6_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_saves);
            TextView team6_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_saveperc);
            TextView team6_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_goalsagainst);
            TextView team6_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_goalsagainstavg);
            TextView team6_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_gamesplayed);
            TextView team6_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_gamesplayedasg);
            TextView team6_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_wins);
            TextView team6_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_6_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 6
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + (160*5))) //when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t6_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t6_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t6_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t6_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t6_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t6_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t6_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t6_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t6_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t6_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t6_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t6_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t6_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t6_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t6_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t6_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t6_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t6_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team6_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team6_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team6_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team6_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team6_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team6_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team6_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team6_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team6_player_name.setText(t6_player_name);
            team6_player_pos.setText(t6_player_pos);
            team6_player_role.setText(t6_player_role);
            team6_player_points.setText(t6_player_points);
            team6_player_goals.setText(t6_player_goals);
            team6_player_assis.setText(t6_player_assis);
            team6_player_ppg.setText(t6_player_ppg);
            team6_player_plmi.setText(t6_player_plmi);
            team6_player_gwg.setText(t6_player_gwg);
            team6_player_gperc.setText(t6_player_gperc);
            team6_player_saves.setText(t6_player_saves);
            team6_player_saveperc.setText(t6_player_saveperc);
            team6_player_ga.setText(t6_player_ga);
            team6_player_gaa.setText(t6_player_gaa);
            team6_player_gp.setText(t6_player_gp);
            team6_player_gpag.setText(t6_player_gpag);
            team6_player_wins.setText(t6_player_wins);
            team6_player_so.setText(t6_player_so);
            //team
            TextView team7_name = (TextView) getView().findViewById(R.id.tv_rsl_team_7_roster);
            TextView team7_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_gp);
            TextView team7_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_wins);
            TextView team7_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_OTW);
            TextView team7_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_OTL);
            TextView team7_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_LOSSES);
            TextView team7_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_POINTS);
            TextView team7_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_7_stats_STREAK);

//players
            String t7_player_name="";
            String t7_player_pos="";
            String t7_player_role="";
            String t7_player_points="";
            String t7_player_goals="";
            String t7_player_assis="";
            String t7_player_ppg="";
            String t7_player_plmi="";
            String t7_player_gwg="";
            String t7_player_gperc="";
            String t7_player_saves="";
            String t7_player_saveperc="";
            String t7_player_ga="";
            String t7_player_gaa="";
            String t7_player_gp="";
            String t7_player_gpag="";
            String t7_player_wins="";
            String t7_player_so="";

            TextView team7_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_name);
            TextView team7_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_pos);
            TextView team7_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_role);
            TextView team7_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_pts);
            TextView team7_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_goals);
            TextView team7_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_assists);
            TextView team7_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_ppg);
            TextView team7_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_plusmin);
            TextView team7_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_gwg);
            TextView team7_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_goalperc);
            TextView team7_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_saves);
            TextView team7_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_saveperc);
            TextView team7_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_goalsagainst);
            TextView team7_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_goalsagainstavg);
            TextView team7_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_gamesplayed);
            TextView team7_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_gamesplayedasg);
            TextView team7_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_wins);
            TextView team7_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_7_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 7
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + (160*6))) //when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t7_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t7_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t7_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t7_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t7_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t7_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t7_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t7_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t7_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t7_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t7_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t7_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t7_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t7_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t7_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t7_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t7_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t7_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team7_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team7_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team7_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team7_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team7_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team7_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team7_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team7_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team7_player_name.setText(t7_player_name);
            team7_player_pos.setText(t7_player_pos);
            team7_player_role.setText(t7_player_role);
            team7_player_points.setText(t7_player_points);
            team7_player_goals.setText(t7_player_goals);
            team7_player_assis.setText(t7_player_assis);
            team7_player_ppg.setText(t7_player_ppg);
            team7_player_plmi.setText(t7_player_plmi);
            team7_player_gwg.setText(t7_player_gwg);
            team7_player_gperc.setText(t7_player_gperc);
            team7_player_saves.setText(t7_player_saves);
            team7_player_saveperc.setText(t7_player_saveperc);
            team7_player_ga.setText(t7_player_ga);
            team7_player_gaa.setText(t7_player_gaa);
            team7_player_gp.setText(t7_player_gp);
            team7_player_gpag.setText(t7_player_gpag);
            team7_player_wins.setText(t7_player_wins);
            team7_player_so.setText(t7_player_so);

            //team
            TextView team8_name = (TextView) getView().findViewById(R.id.tv_rsl_team_8_roster);
            TextView team8_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_gp);
            TextView team8_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_wins);
            TextView team8_otw = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_OTW);
            TextView team8_otl = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_OTL);
            TextView team8_losses = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_LOSSES);
            TextView team8_pts = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_POINTS);
            TextView team8_streak = (TextView) getView().findViewById(R.id.tv_rsl_team_8_stats_STREAK);
//players
            String t8_player_name="";
            String t8_player_pos="";
            String t8_player_role="";
            String t8_player_points="";
            String t8_player_goals="";
            String t8_player_assis="";
            String t8_player_ppg="";
            String t8_player_plmi="";
            String t8_player_gwg="";
            String t8_player_gperc="";
            String t8_player_saves="";
            String t8_player_saveperc="";
            String t8_player_ga="";
            String t8_player_gaa="";
            String t8_player_gp="";
            String t8_player_gpag="";
            String t8_player_wins="";
            String t8_player_so="";

            TextView team8_player_name = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_name);
            TextView team8_player_pos = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_pos);
            TextView team8_player_role = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_role);
            TextView team8_player_points = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_pts);
            TextView team8_player_goals = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_goals);
            TextView team8_player_assis = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_assists);
            TextView team8_player_ppg = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_ppg);
            TextView team8_player_plmi = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_plusmin);
            TextView team8_player_gwg = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_gwg);
            TextView team8_player_gperc = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_goalperc);
            TextView team8_player_saves = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_saves);
            TextView team8_player_saveperc = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_saveperc);
            TextView team8_player_ga = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_goalsagainst);
            TextView team8_player_gaa = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_goalsagainstavg);
            TextView team8_player_gp = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_gamesplayed);
            TextView team8_player_gpag = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_gamesplayedasg);
            TextView team8_player_wins = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_wins);
            TextView team8_player_so = (TextView) getView().findViewById(R.id.tv_rsl_team_8_player_stats_shutouts);
            trcount = 0;
            tdcount = 0;
            teamCount = 0;
            rosterCount = 0;
            maxPlayer = 0;
            teamDone = false;
            //THE TD STARTS AT COUNT = 7
            //TR STARTS AT COUNT = 5
            for(Element temp : tr)
            {
                if (trcount == 5) //found data table
                {
                    for (Element ele : td)
                    {
                        if(tdcount >= (6 + (160*7))) //when the team starts
                        {

                            if(teamDone && maxPlayer < 8)
                            {
                                switch(rosterCount)
                                {
                                    case 0:
                                        t8_player_name += ele.html() + "\n";
                                        break;
                                    case 2:
                                        t8_player_pos += ele.html() + "\n";
                                        break;
                                    case 3:
                                        t8_player_role += ele.html() + "\n";
                                        break;
                                    case 4:
                                        t8_player_points += ele.html() + "\n";
                                        break;
                                    case 5:
                                        t8_player_goals += ele.html() + "\n";
                                        break;
                                    case 6:
                                        t8_player_assis += ele.html() + "\n";
                                        break;
                                    case 7:
                                        t8_player_ppg += ele.html() + "\n";
                                        break;
                                    case 8:
                                        t8_player_plmi += ele.html() + "\n";
                                        break;
                                    case 9:
                                        t8_player_gwg += ele.html() + "\n";
                                        break;
                                    case 10:
                                        t8_player_gperc += ele.html() + "\n";
                                        break;
                                    case 11:
                                        t8_player_saves += ele.html() + "\n";
                                        break;
                                    case 12:
                                        t8_player_saveperc += ele.html() + "\n";
                                        break;
                                    case 13:
                                        t8_player_ga += ele.html() + "\n";
                                        break;
                                    case 14:
                                        t8_player_gaa += ele.html() + "\n";
                                        break;
                                    case 15:
                                        t8_player_gp += ele.html() + "\n";
                                        break;
                                    case 16:
                                        t8_player_gpag += ele.html() + "\n";
                                        break;
                                    case 17:
                                        t8_player_wins += ele.html() + "\n";
                                        break;
                                    case 18:
                                        t8_player_so += ele.html() + "\n";
                                        break;
                                    default:
                                        break;
                                }

                                if(rosterCount < 18)
                                    rosterCount++;
                                else {
                                    rosterCount = 0;
                                    maxPlayer++;
                                }
                            } else
                            {
                                switch (teamCount)
                                {
                                    case 0:
                                        team8_name.setText(ele.html());
                                        break;
                                    case 1:
                                        team8_gp.setText(ele.html());
                                        break;
                                    case 2:
                                        team8_wins.setText(ele.html());
                                        break;
                                    case 3:
                                        team8_otw.setText(ele.html());
                                        break;
                                    case 4:
                                        team8_otl.setText(ele.html());
                                        break;
                                    case 5:
                                        team8_losses.setText(ele.html());
                                        break;
                                    case 6:
                                        team8_pts.setText(ele.html());
                                        break;
                                    case 7:
                                        team8_streak.setText(ele.html());
                                        teamDone = true;
                                        break;
                                    default:
                                        break;

                                }
                                teamCount++;
                            }

                        }
                        tdcount++;
                    }
                }
                trcount++;
            }


            //now set values
            team8_player_name.setText(t8_player_name);
            team8_player_pos.setText(t8_player_pos);
            team8_player_role.setText(t8_player_role);
            team8_player_points.setText(t8_player_points);
            team8_player_goals.setText(t8_player_goals);
            team8_player_assis.setText(t8_player_assis);
            team8_player_ppg.setText(t8_player_ppg);
            team8_player_plmi.setText(t8_player_plmi);
            team8_player_gwg.setText(t8_player_gwg);
            team8_player_gperc.setText(t8_player_gperc);
            team8_player_saves.setText(t8_player_saves);
            team8_player_saveperc.setText(t8_player_saveperc);
            team8_player_ga.setText(t8_player_ga);
            team8_player_gaa.setText(t8_player_gaa);
            team8_player_gp.setText(t8_player_gp);
            team8_player_gpag.setText(t8_player_gpag);
            team8_player_wins.setText(t8_player_wins);
            team8_player_so.setText(t8_player_so);

            mProgressDialog.dismiss();
        }
    }
}

