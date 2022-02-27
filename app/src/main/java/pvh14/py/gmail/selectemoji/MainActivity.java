package pvh14.py.gmail.selectemoji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    GridView IconsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IconsList = findViewById(R.id.myGirdView);
        List<Integer> emojiesList = Arrays.asList(
                8986, 0x1F603, 0x1F605, 0x1F60D, 0x1F60F,
                0x1F618, 0x1F621, 0x1F625, 0x1F628, 0x1F62D,
                0x1F637, 0x1F61D, 0x1F616, 0x1F609, 0x1F60B,
                0x1F635, 0x1F633, 0x1F624, 0x1F61C, 0x1F60A);
        List data = new ArrayList();
        for (int i = 0; i < emojiesList.size(); i++) {
            data.add(new String(Character.toChars(emojiesList.get(i))));
        }
        EmojiServices emojiServices = new EmojiServices(getApplicationContext(), R.layout.emoji, data);
        IconsList.setAdapter(emojiServices);
        List temp = new ArrayList(data);
        final String[] target = {getAndShowRandomEmoji(temp)};
        IconsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView v = (TextView) view;
                String selected = v.getText().toString();
                if(selected.equals(target[0])){
                    temp.remove(target[0]);
                    v.setText("");
                }
                target[0] = getAndShowRandomEmoji(temp);
            }

        });
    }

    private String getAndShowRandomEmoji(List temp) {
        Random rand = new Random();
        String result = temp.get(rand.nextInt(temp.size())).toString();
        TextView target = findViewById(R.id.target);
        target.setText(result);
        return result;
    }
}