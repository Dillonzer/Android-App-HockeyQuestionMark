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
 * The 198 in JSL and 168 in LHL and 157(ithink) in RSL refer to how many TDS 1 team contains.
 * I use this to skip teams to find them in the DOM. Will be creating a constant in the next version.
 * Also will be creating a MAX_PLAYER constant, along with some others..
 */
public class JSLRosterFragment extends Fragment {
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.jsl_ros_fragment, container, false);
        return view;


    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new JsoupRun().execute();
        //CODE GOES HERE
    }

    private class JsoupRun extends AsyncTask<Void, Void, Void> {
        String url = "https://sites.google.com/site/hockeyquestionmarkapp/JSLRoster";
        Elements td;
        Elements tr;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("JSL Rosters");
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
            TextView team1_name = (TextView) getView().findViewById(R.id.tv_jsl_team_1_roster);
            TextView team1_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_gp);
            TextView team1_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_wins);
            TextView team1_otw = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_OTW);
            TextView team1_otl = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_OTL);
            TextView team1_losses = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_LOSSES);
            TextView team1_pts = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_POINTS);
            TextView team1_streak = (TextView) getView().findViewById(R.id.tv_jsl_team_1_stats_STREAK);

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

            TextView team1_player_name = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_name);
            TextView team1_player_pos = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_pos);
            TextView team1_player_role = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_role);
            TextView team1_player_points = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_pts);
            TextView team1_player_goals = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_goals);
            TextView team1_player_assis = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_assists);
            TextView team1_player_ppg = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_ppg);
            TextView team1_player_plmi = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_plusmin);
            TextView team1_player_gwg = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_gwg);
            TextView team1_player_gperc = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_goalperc);
            TextView team1_player_saves = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_saves);
            TextView team1_player_saveperc = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_saveperc);
            TextView team1_player_ga = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_goalsagainst);
            TextView team1_player_gaa = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_goalsagainstavg);
            TextView team1_player_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_gamesplayed);
            TextView team1_player_gpag = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_gamesplayedasg);
            TextView team1_player_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_wins);
            TextView team1_player_so = (TextView) getView().findViewById(R.id.tv_jsl_team_1_player_stats_shutouts);
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

                            if(teamDone && maxPlayer < 10)
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
            TextView team2_name = (TextView) getView().findViewById(R.id.tv_jsl_team_2_roster);
            TextView team2_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_gp);
            TextView team2_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_wins);
            TextView team2_otw = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_OTW);
            TextView team2_otl = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_OTL);
            TextView team2_losses = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_LOSSES);
            TextView team2_pts = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_POINTS);
            TextView team2_streak = (TextView) getView().findViewById(R.id.tv_jsl_team_2_stats_STREAK);

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

            TextView team2_player_name = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_name);
            TextView team2_player_pos = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_pos);
            TextView team2_player_role = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_role);
            TextView team2_player_points = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_pts);
            TextView team2_player_goals = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_goals);
            TextView team2_player_assis = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_assists);
            TextView team2_player_ppg = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_ppg);
            TextView team2_player_plmi = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_plusmin);
            TextView team2_player_gwg = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_gwg);
            TextView team2_player_gperc = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_goalperc);
            TextView team2_player_saves = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_saves);
            TextView team2_player_saveperc = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_saveperc);
            TextView team2_player_ga = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_goalsagainst);
            TextView team2_player_gaa = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_goalsagainstavg);
            TextView team2_player_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_gamesplayed);
            TextView team2_player_gpag = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_gamesplayedasg);
            TextView team2_player_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_wins);
            TextView team2_player_so = (TextView) getView().findViewById(R.id.tv_jsl_team_2_player_stats_shutouts);
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
                        if(tdcount >= (6 + 198))
                        {

                            if(teamDone && maxPlayer < 10)
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
            TextView team3_name = (TextView) getView().findViewById(R.id.tv_jsl_team_3_roster);
            TextView team3_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_gp);
            TextView team3_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_wins);
            TextView team3_otw = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_OTW);
            TextView team3_otl = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_OTL);
            TextView team3_losses = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_LOSSES);
            TextView team3_pts = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_POINTS);
            TextView team3_streak = (TextView) getView().findViewById(R.id.tv_jsl_team_3_stats_STREAK);

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

            TextView team3_player_name = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_name);
            TextView team3_player_pos = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_pos);
            TextView team3_player_role = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_role);
            TextView team3_player_points = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_pts);
            TextView team3_player_goals = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_goals);
            TextView team3_player_assis = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_assists);
            TextView team3_player_ppg = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_ppg);
            TextView team3_player_plmi = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_plusmin);
            TextView team3_player_gwg = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_gwg);
            TextView team3_player_gperc = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_goalperc);
            TextView team3_player_saves = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_saves);
            TextView team3_player_saveperc = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_saveperc);
            TextView team3_player_ga = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_goalsagainst);
            TextView team3_player_gaa = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_goalsagainstavg);
            TextView team3_player_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_gamesplayed);
            TextView team3_player_gpag = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_gamesplayedasg);
            TextView team3_player_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_wins);
            TextView team3_player_so = (TextView) getView().findViewById(R.id.tv_jsl_team_3_player_stats_shutouts);
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
                        if(tdcount >= (6 + 198 + 198))//when the team starts
                        {

                            if(teamDone && maxPlayer < 10)
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
            TextView team4_name = (TextView) getView().findViewById(R.id.tv_jsl_team_4_roster);
            TextView team4_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_gp);
            TextView team4_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_wins);
            TextView team4_otw = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_OTW);
            TextView team4_otl = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_OTL);
            TextView team4_losses = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_LOSSES);
            TextView team4_pts = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_POINTS);
            TextView team4_streak = (TextView) getView().findViewById(R.id.tv_jsl_team_4_stats_STREAK);

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

            TextView team4_player_name = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_name);
            TextView team4_player_pos = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_pos);
            TextView team4_player_role = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_role);
            TextView team4_player_points = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_pts);
            TextView team4_player_goals = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_goals);
            TextView team4_player_assis = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_assists);
            TextView team4_player_ppg = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_ppg);
            TextView team4_player_plmi = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_plusmin);
            TextView team4_player_gwg = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_gwg);
            TextView team4_player_gperc = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_goalperc);
            TextView team4_player_saves = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_saves);
            TextView team4_player_saveperc = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_saveperc);
            TextView team4_player_ga = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_goalsagainst);
            TextView team4_player_gaa = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_goalsagainstavg);
            TextView team4_player_gp = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_gamesplayed);
            TextView team4_player_gpag = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_gamesplayedasg);
            TextView team4_player_wins = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_wins);
            TextView team4_player_so = (TextView) getView().findViewById(R.id.tv_jsl_team_4_player_stats_shutouts);
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
                        if(tdcount >= (6 + (198*3)))//when the team starts
                        {

                            if(teamDone && maxPlayer < 10)
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

            mProgressDialog.dismiss();
        }
    }
}

