package com.example.csc2990_tickerwatchlistmanager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SMSReceiver extends BroadcastReceiver {

    public static final String EXTRA_TICKER = "newTicker";

    private static final Pattern WATCHLIST_PATTERN =
            Pattern.compile("^\\s*Ticker:\\s*<<(.*?)>>\\s*$");

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

        Matcher matcher = WATCHLIST_PATTERN.matcher(body);
        boolean validFormat = matcher.matches();
        boolean validTicker = false;
        String ticker = null;

        if (validFormat) {
            String raw = matcher.group(1);
            if (raw.matches("^[A-Za-z]+$")) {
                ticker = raw.toUpperCase();
                validTicker = true;
            }
        }

        Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (!validFormat) {
            Toast.makeText(context, "No valid watchlist entry found", Toast.LENGTH_LONG).show();
            context.startActivity(launchIntent);
            return;
        }

        if (!validTicker) {
            Toast.makeText(context, "Invalid ticker format", Toast.LENGTH_LONG).show();
            context.startActivity(launchIntent);
            return;
        }
        launchIntent.putExtra(EXTRA_TICKER, ticker);
        context.startActivity(launchIntent);
    }
}
