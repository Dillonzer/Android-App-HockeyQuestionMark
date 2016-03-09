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
 * Created by Dillon on 2016-02-13.
 * rank team record points
 */
public class JSLStandings extends Fragment {
    ProgressDialog mProgressDialog;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.jsl_standings_fragment, container, false);

        return view;

    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        new JsoupRun().execute();
        //CODE GOES HERE
    }



    private class JsoupRun extends AsyncTask<Void, Void, Void> {
        String url = "https://sites.google.com/site/hockeyquestionmarkapp/JSLStandings";
        Elements td;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setTitle("JSL Standings");
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

            String s_rank="";
            String s_team ="";
            String s_record="";
            String s_points="";
            int count = 0;
            int rowCounter=0;

            TextView rank = (TextView) getView().findViewById(R.id.tv_jsl_standings_rank_data);
            TextView team = (TextView) getView().findViewById(R.id.tv_jsl_standings_team_data);
            TextView record = (TextView) getView().findViewById(R.id.tv_jsl_standings_record_data);
            TextView points = (TextView) getView().findViewById(R.id.tv_jsl_standings_points_data);

            //THE TABLE STARTS AT COUNT = 6
            for(Element ele : td)
            {

                if(count >= 6)
                {
                    //add to string
                    switch (rowCounter)
                    {
                        case 0:
                            s_rank += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 1:
                            s_team += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 2:
                            s_record += ele.html() + "\n";
                            rowCounter++;
                            break;
                        case 3:
                            s_points += ele.html() + "\n";
                            rowCounter=0;
                            break;
                        default:
                            break;
                    }
                }


                count++;
            }

            rank.setText(s_rank);
            team.setText(s_team);
            record.setText(s_record);
            points.setText(s_points);

            mProgressDialog.dismiss();
        }
    }
}
