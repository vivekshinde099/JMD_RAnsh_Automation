package com.jio.ranshjmd.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.
        TextView;


import java.util.ArrayList;
import java.util.List;

import com.jio.ranshjmd.Checking_issue;
import com.jio.ranshjmd.Models.Names;
import com.jio.ranshjmd.R;
import com.jio.ranshjmd.SR_Form;

public class NamesAdapter extends ArrayAdapter<Names> {

    Context context;
    int resource, textViewResourceId;
    List<Names> items, tempItems, suggestions;
    public static String nullvalue = "";

    public NamesAdapter(Context context, int resource, int textViewResourceId, List<Names> items) {
        super(context, resource, textViewResourceId, items);
        this.context = context;
        this.resource = resource;
        this.textViewResourceId = textViewResourceId;
        this.items = items;
      //  this.suggestions = suggestions;
        tempItems = new ArrayList<Names>(items); // this makes the difference.
        suggestions = new ArrayList<Names>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.autocomplete_item, parent, false);
        }
        TextView lblName = (TextView) view.findViewById(R.id.lbl_name);
        TextView other = (TextView) view.findViewById(R.id.others);
        Names names = items.get(position);
        if (names != null) {
            if (lblName != null)
                lblName.setText(names.name);
        }
        other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SR_Form.class);
                context.startActivity(intent);
            }
        });

        lblName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Names names = items.get(position);
                if (names != null) {

                    if (lblName != null){
                        lblName.setText(names.name);
                        String itemName=lblName.getText().toString();
                        switch (itemName){
                            case "Agent Authentication Failed from OID" :
                                Intent A = new Intent(context, Checking_issue.class);
                                A.putExtra("A",itemName);
                                A.putExtra("Close","Close");
                                context.startActivity(A);
                                break;

                            case "Store Code Mis-Match" :
                                Intent C = new Intent(context, Checking_issue.class);
                                C.putExtra("A",itemName);
                                C.putExtra("Close","Close");
                                context.startActivity(C);
                                break;

                            case "Payment not found" :
                                Intent D = new Intent(context, Checking_issue.class);
                                D.putExtra("A",itemName);
                                D.putExtra("Close","Close");
                                context.startActivity(D);
                                break;

                            case "Gst Table Does not exist. Do you want to try again?" :
                                Intent E = new Intent(context, Checking_issue.class);
                                E.putExtra("A",itemName);
                                E.putExtra("Close","Close");
                                context.startActivity(E);
                                break;

                            case "Failed to configure RPOS.db" :
                                Intent F = new Intent(context, Checking_issue.class);
                                F.putExtra("A",itemName);
                                F.putExtra("Close","Close");
                                context.startActivity(F);
                                break;

                            case "Ledger / Order Status" :
                                Intent G = new Intent(context, Checking_issue.class);
                                G.putExtra("A",itemName);
                                G.putExtra("Close","Close");
                                context.startActivity(G);
                                break;

                            case "User is not present in OIAM" :
                                Intent H = new Intent(context, Checking_issue.class);
                                H.putExtra("A",itemName);
                                H.putExtra("Close","Close");
                                context.startActivity(H);
                                break;

                            case "connection refused." :
                                Intent I = new Intent(context, Checking_issue.class);
                                I.putExtra("A",itemName);
                                I.putExtra("Close","Close");
                                context.startActivity(I);
                                break;

                            case "I want to know my POS ID" :
                                Intent J = new Intent(context, Checking_issue.class);
                                J.putExtra("A",itemName);
                                J.putExtra("Close","Close");
                                context.startActivity(J);
                                break;

                            case "Touch screen not working" :
                                Intent K = new Intent(context, Checking_issue.class);
                                K.putExtra("A",itemName);
                                K.putExtra("Close","Close");
                                context.startActivity(K);
                                break;

                            case "Tablet Battery getting drained (Fast Discharge)" :
                                Intent L = new Intent(context, Checking_issue.class);
                                L.putExtra("A",itemName);
                                L.putExtra("Close","Close");
                                context.startActivity(L);
                                break;

                            case "Order pending in ASP" :
                                Intent M = new Intent(context, Checking_issue.class);
                                M.putExtra("A",itemName);
                                M.putExtra("Close","Close");
                                context.startActivity(M);
                                break;

                            case "Add to cart button not enabled" :
                                Intent N = new Intent(context, Checking_issue.class);
                                N.putExtra("A",itemName);
                                N.putExtra("Close","Close");
                                context.startActivity(N);
                                break;

                            case "Pin code not available" :
                                Intent O = new Intent(context, Checking_issue.class);
                                O.putExtra("A",itemName);
                                O.putExtra("Close","Close");
                                context.startActivity(O);
                                break;

                            case "Web page not available" :
                                Intent P = new Intent(context, Checking_issue.class);
                                P.putExtra("A",itemName);
                                P.putExtra("Close","Close");
                                context.startActivity(P);
                                break;

                            case "Order Placement Process through ASP" :
                                Intent Q = new Intent(context, Checking_issue.class);
                                Q.putExtra("A",itemName);
                                Q.putExtra("Close","Close");
                                context.startActivity(Q);
                                break;

                            case "Unable to search product in ASP." :
                                Intent R = new Intent(context, Checking_issue.class);
                                R.putExtra("A",itemName);
                                R.putExtra("Close","Close");
                                context.startActivity(R);
                                break;

                            case "Error checking out inventory. Please try after sometime." :
                                Intent S = new Intent(context, Checking_issue.class);
                                S.putExtra("A",itemName);
                                S.putExtra("Close","Close");
                                context.startActivity(S);
                                break;

                            case "App is stuck on blue screen" :
                                Intent T = new Intent(context, Checking_issue.class);
                                T.putExtra("A",itemName);
                                T.putExtra("Close","Close");
                                context.startActivity(T);
                                break;

                            case "Unable to click on Buy Now option" :
                                Intent U = new Intent(context, Checking_issue.class);
                                U.putExtra("A",itemName);
                                U.putExtra("Close","Close");
                                context.startActivity(U);
                                break;

                            case "User account is locked/disabled" :
                                Intent V = new Intent(context, Checking_issue.class);
                                V.putExtra("A",itemName);
                                V.putExtra("Close","Close");
                                context.startActivity(V);
                                break;

                            case "Unable to login in JMD" :
                                Intent W = new Intent(context, Checking_issue.class);
                                W.putExtra("A",itemName);
                                W.putExtra("Close","Close");
                                context.startActivity(W);
                                break;

                            case "Invalid User Credentials" :
                                Intent X = new Intent(context, Checking_issue.class);
                                X.putExtra("A",itemName);
                                X.putExtra("Close","Close");
                                context.startActivity(X);
                                break;

                            case "504 gateway timeout" :
                                Intent Y = new Intent(context, Checking_issue.class);
                                Y.putExtra("A",itemName);
                                Y.putExtra("Close","Close");
                                context.startActivity(Y);
                                break;

                            case "404 app config not found" :
                                Intent Z = new Intent(context, Checking_issue.class);
                                Z.putExtra("A",itemName);
                                Z.putExtra("Close","Close");
                                context.startActivity(Z);
                                break;

                            case "Unable to compare products" :
                                Intent AA = new Intent(context, Checking_issue.class);
                                AA.putExtra("A",itemName);
                                AA.putExtra("Close","Close");
                                context.startActivity(AA);
                                break;

                            case "No data found, while entering OTP" :
                                Intent AB = new Intent(context, Checking_issue.class);
                                AB.putExtra("A",itemName);
                                AB.putExtra("Close","Close");
                                context.startActivity(AB);
                                break;

                            case "Order cancellation process" :
                                Intent AC = new Intent(context, Checking_issue.class);
                                AC.putExtra("A",itemName);
                                AC.putExtra("Close","Close");
                                context.startActivity(AC);
                                break;

                            case "Amount refund process" :
                                Intent AD = new Intent(context, Checking_issue.class);
                                AD.putExtra("A",itemName);
                                AD.putExtra("Close","Close");
                                context.startActivity(AD);
                                break;

                            case "Buffering/Redirecting to Login screen/SSL pinning failed" :
                                Intent AE = new Intent(context, Checking_issue.class);
                                AE.putExtra("A",itemName);
                                AE.putExtra("Close","Close");
                                context.startActivity(AE);
                                break;

                            case "product price mismatch" :
                                Intent AF = new Intent(context, Checking_issue.class);
                                AF.putExtra("A",itemName);
                                AF.putExtra("Close","Close");
                                context.startActivity(AF);
                                break;

                            case "Failed reason : validation failed" :
                                Intent AG = new Intent(context, Checking_issue.class);
                                AG.putExtra("A",itemName);
                                AG.putExtra("Close","Close");
                                context.startActivity(AG);
                                break;

                            case "Online Order MASP Process" :
                                Intent AH = new Intent(context, Checking_issue.class);
                                AH.putExtra("A",itemName);
                                AH.putExtra("Close","Close");
                                context.startActivity(AH);
                                break;

                            case "You cannot void selected article" :
                                Intent AI = new Intent(context, Checking_issue.class);
                                AI.putExtra("A",itemName);
                                AI.putExtra("Close","Close");
                                context.startActivity(AI);
                                break;

                            case "OTP could not be received":
                                Intent AJ = new Intent(context, Checking_issue.class);
                                AJ.putExtra("A",itemName);
                                AJ.putExtra("Close","Close");
                                context.startActivity(AJ);
                                break;

                            case "Amount requested does not match online payment" :
                                Intent AK = new Intent(context, Checking_issue.class);
                                AK.putExtra("A",itemName);
                                AK.putExtra("Close","Close");
                                context.startActivity(AK);
                                break;

                            case "Some technical error occurred" :
                                Intent AL = new Intent(context, Checking_issue.class);
                                AL.putExtra("A",itemName);
                                AL.putExtra("Close","Close");
                                context.startActivity(AL);
                                break;

                            case "KYC failed" :
                                Intent AM = new Intent(context, Checking_issue.class);
                                AM.putExtra("A",itemName);
                                AM.putExtra("Close","Close");
                                context.startActivity(AM);
                                break;

                            case "504 gateway time out" :
                                Intent AN = new Intent(context, Checking_issue.class);
                                AN.putExtra("A",itemName);
                                AN.putExtra("Close","Close");
                                context.startActivity(AN);
                                break;

                            case "Your loan application is rejected - APPROVAL CRITERIA NOT MET" :
                                Intent AO = new Intent(context, Checking_issue.class);
                                AO.putExtra("A",itemName);
                                AO.putExtra("Close","Close");
                                context.startActivity(AO);
                                break;

                            case "Screen goes blank after selecting Bundle Phone option" :
                                Intent AP = new Intent(context, Checking_issue.class);
                                AP.putExtra("A",itemName);
                                AP.putExtra("Close","Close");
                                context.startActivity(AP);
                                break;

                            case "We are processing your data. Please wait for some time and retry" :
                                Intent AQ = new Intent(context, Checking_issue.class);
                                AQ.putExtra("A",itemName);
                                AQ.putExtra("Close","Close");
                                context.startActivity(AQ);
                                break;

                            case "Number is not eligible" :
                                Intent AR = new Intent(context, Checking_issue.class);
                                AR.putExtra("A",itemName);
                                AR.putExtra("Close","Close");
                                context.startActivity(AR);
                                break;

                            case "Your loan application is under process. Please try after sometime" :
                                Intent AS = new Intent(context, Checking_issue.class);
                                AS.putExtra("A",itemName);
                                AS.putExtra("Close","Close");
                                context.startActivity(AS);
                                break;

                            case "You cannot sell this device from device sale" :
                                Intent AT = new Intent(context, Checking_issue.class);
                                AT.putExtra("A",itemName);
                                AT.putExtra("Close","Close");
                                context.startActivity(AT);
                                break;

                            case "Payment deducted, EJ not generated" :
                                Intent AU = new Intent(context, Checking_issue.class);
                                AU.putExtra("A",itemName);
                                AU.putExtra("Close","Close");
                                context.startActivity(AU);
                                break;

                            case "Item Not Found" :
                                Intent AV = new Intent(context, Checking_issue.class);
                                AV.putExtra("A",itemName);
                                AV.putExtra("Close","Close");
                                context.startActivity(AV);
                                break;

                            case "Pincode Not Found" :
                                Intent AW = new Intent(context, Checking_issue.class);
                                AW.putExtra("A",itemName);
                                AW.putExtra("Close","Close");
                                context.startActivity(AW);
                                break;

                            case "Error in call while sending the payment link" :
                                Intent AX = new Intent(context, Checking_issue.class);
                                AX.putExtra("A",itemName);
                                AX.putExtra("Close","Close");
                                context.startActivity(AX);
                                break;

                            case "Error in Call after completion of payment by customer" :
                                Intent AY = new Intent(context, Checking_issue.class);
                                AY.putExtra("A",itemName);
                                AY.putExtra("Close","Close");
                                context.startActivity(AY);
                                break;

                            case "Tax Details not configured" :
                                Intent AZ = new Intent(context, Checking_issue.class);
                                AZ.putExtra("A",itemName);
                                AZ.putExtra("Close","Close");
                                context.startActivity(AZ);
                                break;

                            case "Article price 0 (Zero)" :
                                Intent BA = new Intent(context, Checking_issue.class);
                                BA.putExtra("A",itemName);
                                BA.putExtra("Close","Close");
                                context.startActivity(BA);
                                break;

                            case "MOP not visible" :
                                Intent BB = new Intent(context, Checking_issue.class);
                                BB.putExtra("A",itemName);
                                BB.putExtra("Close","Close");
                                context.startActivity(BB);
                                break;

                            case "Failed to get Response" :
                                Intent BC = new Intent(context, Checking_issue.class);
                                BC.putExtra("A",itemName);
                                BC.putExtra("Close","Close");
                                context.startActivity(BC);
                                break;

                            case "Session Expired" :
                                Intent BD = new Intent(context, Checking_issue.class);
                                BD.putExtra("A",itemName);
                                BD.putExtra("Close","Close");
                                context.startActivity(BD);
                                break;

                            case "Tablet over heating issue" :
                                Intent BE = new Intent(context, Checking_issue.class);
                                BE.putExtra("A",itemName);
                                BE.putExtra("Close","Close");
                                context.startActivity(BE);
                                break;

                            case "TV Display is Not Working" :
                                Intent BF = new Intent(context, Checking_issue.class);
                                BF.putExtra("A",itemName);
                                BF.putExtra("Close","Close");
                                context.startActivity(BF);
                                break;

                            case "Tablet is not getting charged through POS stand" :
                                Intent BG = new Intent(context, Checking_issue.class);
                                BG.putExtra("A",itemName);
                                BG.putExtra("Close","Close");
                                context.startActivity(BG);
                                break;

                            case "Cursor visible on tablet screen" :
                                Intent BH = new Intent(context, Checking_issue.class);
                                BH.putExtra("A",itemName);
                                BH.putExtra("Close","Close");
                                context.startActivity(BH);
                                break;

                            case "STB Hardware related Issues" :
                                Intent BI = new Intent(context, Checking_issue.class);
                                BI.putExtra("A",itemName);
                                BI.putExtra("Close","Close");
                                context.startActivity(BI);
                                break;


                            case "Print not coming from printer" :
                                Intent BK = new Intent(context, Checking_issue.class);
                                BK.putExtra("A",itemName);
                                BK.putExtra("Close","Close");
                                context.startActivity(BK);
                                break;



                        }
                    }
                }

            }
        });


       if(items.size()-1==position)
        {
            TextView others = view.findViewById(R.id.others);
            others.setVisibility(View.VISIBLE);
        }else {
            TextView others = view.findViewById(R.id.others);
            others.setVisibility(View.GONE);
        }
        return view;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }
    /**
     * Custom Filter implementation for custom suggestions we provide.
     */
    Filter nameFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            String str = ((Names) resultValue).name;
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();

                for (Names names : tempItems) {
                    if (names.name.toLowerCase().contains(constraint.toString().toLowerCase())) {
                        suggestions.add(names);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            List<Names> filterList = (ArrayList<Names>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (Names names : filterList) {
                    add(names);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
