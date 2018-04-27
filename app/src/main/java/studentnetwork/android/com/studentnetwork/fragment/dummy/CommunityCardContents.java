package studentnetwork.android.com.studentnetwork.fragment.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import studentnetwork.android.com.studentnetwork.bo.Content;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class CommunityCardContents {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Content> ITEMS = new ArrayList<Content>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, Content> ITEM_MAP = new HashMap<String, Content>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createDummyItem(i));
//        }
    }

    private static void addItem(Content content) {
        ITEMS.add(content);
        ITEM_MAP.put(String.valueOf(content.getId()), content);
    }

//    private static Content createContent(int position) {
//        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
//    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }


}
