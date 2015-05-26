package com.ivangusef.data.entity.mapper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.ivangusef.data.entity.RssEntity;

import org.apache.commons.lang3.StringEscapeUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan_Gusev1 on 5/26/2015.
 */
public class ResponseRssMapper {

    private static final String TAG = ResponseRssMapper.class.getSimpleName();

    private static final String TAG_ITEM        = "item";
    private static final String TAG_TITLE       = "title";
    private static final String TAG_DESCRIPTION = "description";
    private static final String TAG_PUB_DATE    = "pubDate";
    private static final String TAG_LINK        = "link";

    public ResponseRssMapper() {
    }

    @NonNull
    public List<RssEntity> transform(@NonNull final String response) {
        final List<RssEntity> rssEntityList = new ArrayList<>();
        try {
            final XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            final XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(response));

            RssEntity rssEntity = new RssEntity();
            String tag, text = "";
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                tag = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tag.equalsIgnoreCase(TAG_ITEM)) {
                            rssEntity = new RssEntity();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = StringEscapeUtils.unescapeHtml4(parser.getText());
                        break;
                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase(TAG_ITEM)) {
                            rssEntityList.add(rssEntity);
                        } else if (tag.equalsIgnoreCase(TAG_TITLE)) {
                            rssEntity.setTitle(text);
                        } else if (tag.equalsIgnoreCase(TAG_DESCRIPTION)) {
                            rssEntity.setDescription(text);
                        } else if (tag.equalsIgnoreCase(TAG_PUB_DATE)) {
                            rssEntity.setPubDate(text);
                        } else if (tag.equalsIgnoreCase(TAG_LINK)) {
                            rssEntity.setLink(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }


        } catch (XmlPullParserException | IOException e) {
            Log.w(TAG, e.getMessage(), e);
        }
        return rssEntityList;
    }
}
