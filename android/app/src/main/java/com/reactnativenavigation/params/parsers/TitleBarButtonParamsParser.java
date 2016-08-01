package com.reactnativenavigation.params.parsers;

import android.os.Bundle;

import com.reactnativenavigation.params.StyleParams;
import com.reactnativenavigation.params.TitleBarButtonParams;
import com.reactnativenavigation.react.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class TitleBarButtonParamsParser extends Parser {
    public List<TitleBarButtonParams> parseButtons(Bundle params) {
        List<TitleBarButtonParams> result = new ArrayList<>();

        for (String key : params.keySet()) {
            result.add(parseSingleButton(params.getBundle(key)));
        }
        return result;
    }

    public TitleBarButtonParams parseSingleButton(Bundle bundle) {
        TitleBarButtonParams result = new TitleBarButtonParams();
        result.label = bundle.getString("title");
        if (hasKey(bundle,"icon")) {
            result.icon = ImageLoader.loadImage(bundle.getString("icon"));
        }
        result.color = new StyleParams.Color(ColorParser.parse(bundle.getString("color")));
        result.showAsAction = parseShowAsAction(bundle.getString("showAsAction"));
        result.enabled = !bundle.getBoolean("disabled", false);
        result.eventId = bundle.getString("id");
        return result;
    }

    private static TitleBarButtonParams.ShowAsAction parseShowAsAction(String showAsAction) {
        if (showAsAction == null) {
            return TitleBarButtonParams.ShowAsAction.IfRoom;
        }

        switch (showAsAction) {
            case "always":
                return TitleBarButtonParams.ShowAsAction.Always;
            case "never":
                return TitleBarButtonParams.ShowAsAction.Never;
            case "withText":
                return TitleBarButtonParams.ShowAsAction.WithText;
            case "ifRoom":
            default:
                return TitleBarButtonParams.ShowAsAction.IfRoom;
        }
    }
}
