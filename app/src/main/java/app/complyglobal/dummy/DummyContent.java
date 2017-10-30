package app.complyglobal.dummy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.complyglobal.utils.Constants;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {


    public static List<DummyItem> ITEMS = new ArrayList<DummyItem>();
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();
    public static Calendar calendar=Calendar.getInstance();
    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(DummyItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DummyItem createDummyItem(int position) {
        return new DummyItem(String.valueOf(position), "File Annual Return every calendar year on or before 1st February File Annual Return every calendar year on or before 1st February" + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        switch (position){
            case 1:
                calendar.add(Calendar.DAY_OF_MONTH,position);
                break;
            case 5:
                calendar.add(Calendar.DAY_OF_MONTH,position);
                break;
            case 10:
                calendar.add(Calendar.DAY_OF_MONTH,position);
                break;
            case 15:
                calendar.add(Calendar.DAY_OF_MONTH,position);
                break;
            case 20:
                calendar.add(Calendar.DAY_OF_MONTH,position);
                break;
        }
        return Constants.simpleDateFormat.format(calendar.getTime());
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
        public final String content;
        public final String date;
        public DummyItem(String id, String content, String date) {
            this.id = id;
            this.content = content;
            this.date = date;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}
