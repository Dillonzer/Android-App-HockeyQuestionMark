package dillonbast.hockeyquestionmark.hqm;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by Dillon on 2016-02-08.
 */
public class LHLScheduleFragment extends Fragment {
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.lhl_sched_fragment, container, false);

        return view;

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new JsoupRun().execute();
        //CODE GOES HERE

    }



    private class JsoupRun extends AsyncTask<Void, Void, Void> {
        String url = "https://sites.google.com/site/hockeyquestionmarkapp/LHLSched";
        Elements td;
        //RelativeLayout rl = (RelativeLayout) getView().findViewById(R.id.rl_lhl_schedule);
        //int numOfTvs = 5;




        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("LHL Schedule");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document document = Jsoup.connect(url).get();
                // Get all the TR
                td = document.getElementsByTag("td");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // CODE GOES HERE
            int count = 0;
            int rowCounter = 0;
            String s_week="";
            String s_date="";
            String s_day="";
            String s_time="";
            String s_away="";
            String s_atsign="";
            String s_home="";
            String s_result="";

            //THE TABLE STARTS AT COUNT = 6
            for(Element ele : td)
            {

                if(count >= 6)
                {
                    //add to string
                    switch (rowCounter)
                    {
                        case 0:
                            s_week += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 1:
                            s_date += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 2:
                            s_day += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 3:
                            s_time += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 4:
                            s_away += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 5:
                            s_atsign += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 6:
                            s_home += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 7:
                            s_result += ele.html() + "\n";
                            rowCounter=0;
                            break;
                        default:
                            break;
                    }
                }


                count++;
            }


            TextView week = (TextView) getView().findViewById(R.id.tv_lhl_schedule_week_data);
            TextView date = (TextView) getView().findViewById(R.id.tv_lhl_schedule_date_data);
            TextView day = (TextView) getView().findViewById(R.id.tv_lhl_schedule_day_data);
            TextView time = (TextView) getView().findViewById(R.id.tv_lhl_schedule_time_data);
            TextView away = (TextView) getView().findViewById(R.id.tv_lhl_schedule_away_data);
            TextView atsign = (TextView) getView().findViewById(R.id.tv_lhl_schedule_atsign_data);
            TextView home = (TextView) getView().findViewById(R.id.tv_lhl_schedule_home_data);
            TextView resulttv = (TextView) getView().findViewById(R.id.tv_lhl_schedule_result_data);

            week.setText(s_week);
            date.setText(s_date);
            day.setText(s_day);
            time.setText(s_time);
            away.setText(s_away);
            atsign.setText(s_atsign);
            home.setText(s_home);
            resulttv.setText(s_result);

            mProgressDialog.dismiss();
        }
    }
}


