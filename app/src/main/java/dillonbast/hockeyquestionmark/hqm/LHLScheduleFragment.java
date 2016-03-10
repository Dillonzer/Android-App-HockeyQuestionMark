package dillonbast.hockeyquestionmark.hqm;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Typeface;
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
        RelativeLayout rl = (RelativeLayout) getView().findViewById(R.id.rl_lhl_schedule);
        int numOfTvs = 10000;




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
            String s_dateCheck = "";
            boolean firstGame = true;
            boolean newRow = true;
            TextView[] array = new TextView[numOfTvs];
            RelativeLayout.LayoutParams[] params = new RelativeLayout.LayoutParams[numOfTvs];
            int i_tvCount = 1;
            int i_gameCount = 0;

//TODO: add dynamic imageviews will little team logos

            for(Element ele : td)
            {
                if (count >= 6) //table starts at count 6
                    {
                    //week,date,day,time,away,at,home,result
                    switch(rowCounter)
                    {
                        case 0:
                            //ignore week
                            rowCounter++;
                            break;
                        case 1: //date
                            //check if new date for new first game

                            if(!s_dateCheck.equals(ele.text()) && !ele.text().equals(""))
                            {
                                newRow = true;
                                i_gameCount = 1;
                            }

                            if(firstGame)
                            {
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                                array[i_tvCount].setPadding(0, 250, 0, 0);
                                s_dateCheck = ele.text();
                                i_tvCount++;
                            }
                            else if (newRow)
                            {
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount - 1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                                s_dateCheck = ele.text();
                                i_tvCount++;
                            }


                            rowCounter++;
                            break;
                        case 2: //day
                            if(firstGame)
                            {
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(" - " + ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount - 1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                                array[i_tvCount].setPadding(0, 250, 0, 0);
                                i_tvCount++;
                            }else if (newRow)
                            {
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(" - " + ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount - 2].getId());
                                params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount - 1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                                s_dateCheck = ele.text();
                                i_tvCount++;
                            }
                            rowCounter++;
                            break;
                        case 3: //time
                            if(firstGame || newRow)
                            {
                                //new tv for time,  padding of 250 right
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                array[i_tvCount].setPadding(0, 0, 250, 0);

                                //add below day
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            } else {
                                //new tv for time, right_of old time,
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                array[i_tvCount].setPadding(0, 0, 250, 0);

                                //add below day and to the right of the old time
                                if ((5 * i_gameCount + 1) - i_tvCount > 0)
                                {
                                    params[i_tvCount].addRule(RelativeLayout.BELOW, array[(5 * i_gameCount + 1) - i_tvCount].getId()); //old day
                                } else
                                {
                                    params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount - (5* i_gameCount + 1)].getId()); //old day
                                }
                                params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount - 5].getId()); //old time
                                params[i_tvCount].addRule(RelativeLayout.ALIGN_BASELINE, array[i_tvCount - 5].getId()); //old time
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            }
                            i_tvCount++;
                            rowCounter++;
                            break;
                        case 4: //away
                            if(firstGame || newRow)
                            {
                                //new tv for away not right_of
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTypeface(null, Typeface.BOLD);
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.blue));
                                //add below time
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            } else
                            {
                                //new tv for away, below new time, right of old time
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTypeface(null, Typeface.BOLD);
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.blue));
                                //add below new time and right of old time
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-1].getId()); //new time

                                params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount - 3].getId());
                                params[i_tvCount].addRule(RelativeLayout.ALIGN_BASELINE, array[i_tvCount - 3].getId());
                                params[i_tvCount].addRule(RelativeLayout.ALIGN_LEFT, array[i_tvCount - 1].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            }
                            i_tvCount++;
                            rowCounter++;
                            break;
                        case 5: //at
                            //new tv for atsign right_of current away
                            params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            array[i_tvCount] = new TextView(getActivity());
                            array[i_tvCount].setTag(i_tvCount);
                            array[i_tvCount].setId(i_tvCount);
                            array[i_tvCount].setText(ele.text());
                            array[i_tvCount].setTextColor(getResources().getColor(R.color.black));

                            //add below time and right of away
                            params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-2].getId()); //new time
                            params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount-1].getId()); //away
                            params[i_tvCount].addRule(RelativeLayout.ALIGN_BASELINE, array[i_tvCount-1].getId()); //away
                            array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            i_tvCount++;
                            rowCounter++;
                            break;
                        case 6: //red
                            //new tv for home right of current atsign
                            params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            array[i_tvCount] = new TextView(getActivity());
                            array[i_tvCount].setTag(i_tvCount);
                            array[i_tvCount].setId(i_tvCount);
                            array[i_tvCount].setText(ele.text());
                            array[i_tvCount].setTypeface(null, Typeface.BOLD);
                            array[i_tvCount].setTextColor(getResources().getColor(R.color.red));
                            array[i_tvCount].setPadding(0, 0, 250, 0);

                            //add below time and right of away
                            params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-3].getId()); //new time
                            params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount-1].getId()); //atsign
                            params[i_tvCount].addRule(RelativeLayout.ALIGN_BASELINE, array[i_tvCount-1].getId()); //atsign
                            array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            i_tvCount++;
                            rowCounter++;
                            break;
                        case 7: //result
                            if(firstGame || newRow )
                            {
                                //new tv for result have padding bottom = 15 below away team
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                array[i_tvCount].setPadding(0, 0, 250, 0);

                                //add below home team
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount - 1].getId()); //home
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                                firstGame = false;
                                newRow = false;
                            }else
                            {
                                //new tv for result right of old time below away team
                                params[i_tvCount] = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                                array[i_tvCount] = new TextView(getActivity());
                                array[i_tvCount].setTag(i_tvCount);
                                array[i_tvCount].setId(i_tvCount);
                                array[i_tvCount].setText(ele.text());
                                array[i_tvCount].setTextColor(getResources().getColor(R.color.black));
                                array[i_tvCount].setPadding(0, 0, 250, 0);
                                //add below home team
                                params[i_tvCount].addRule(RelativeLayout.BELOW, array[i_tvCount-1].getId()); //home
                                params[i_tvCount].addRule(RelativeLayout.ALIGN_BASELINE, array[i_tvCount-5].getId()); //home
                                params[i_tvCount].addRule(RelativeLayout.RIGHT_OF, array[i_tvCount-5].getId()); //old time
                                params[i_tvCount].addRule(RelativeLayout.ALIGN_LEFT, array[i_tvCount - 3].getId());
                                array[i_tvCount].setLayoutParams(params[i_tvCount]);
                            }
                            i_tvCount++;
                            rowCounter=0;
                            i_gameCount++;
                            break;
                    }
                }
                count++;
            }

//addViews
            for(int p = 1; p < i_tvCount; p++)
            {
                rl.addView(array[p],params[p]);
            }

            mProgressDialog.dismiss();
        }
    }
}


