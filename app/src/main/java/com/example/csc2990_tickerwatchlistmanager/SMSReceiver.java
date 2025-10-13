package com.example.csc2990_tickerwatchlistmanager;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.provider.Telephony;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SMSReceiver extends BroadcastReceiver {

    private static final Pattern WATCHLIST_PATTERN =
            Pattern.compile("^\\s*Ticker:\\s*<<([A-Za-z]+)>>\\s*$");

    public static final String EXTRA_FROM_SMS = "extra_from_sms";
    public static final String EXTRA_VALID     = "extra_valid";
    public static final String EXTRA_TICKER    = "extra_ticker";
    public static final String EXTRA_OPEN_NOW  = "extra_open_now";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) return;

        StringBuilder fullMsg = new StringBuilder();
        for (SmsMessage msg : Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            if (msg != null && msg.getMessageBody() != null) {
                fullMsg.append(msg.getMessageBody());
            }
        }
        String body = fullMsg.toString().trim();

        boolean isValid = false;
        String ticker = null;

        Matcher m = WATCHLIST_PATTERN.matcher(body);
        if (m.matches()) {
            String raw = m.group(1);

            if (raw.matches("^[A-Za-z]+$")) {
                ticker = raw.toUpperCase();
                isValid = true;
            }
        }

        Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        launchIntent.putExtra(EXTRA_FROM_SMS, true);
        launchIntent.putExtra(EXTRA_VALID, isValid);
        if (isValid) {
            launchIntent.putExtra(EXTRA_TICKER, ticker);
            launchIntent.putExtra(EXTRA_OPEN_NOW, true);
        }

        context.startActivity(launchIntent);
    }
}
