package com.blackbelt.bindings.recyclerviewbindings;

import androidx.annotation.IntDef;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LayoutManagers {

    @IntDef({LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Orientation {
    }

    public interface LayoutManagerFactory {

        RecyclerView.LayoutManager create(RecyclerView recyclerView);
    }

    public static LayoutManagerFactory linear() {
        return recyclerView -> new LinearLayoutManager(recyclerView.getContext());
    }

    public static LayoutManagerFactory linear(@Orientation final int orientation, final boolean reverseLayout) {
        return recyclerView -> new LinearLayoutManager(recyclerView.getContext(), orientation, reverseLayout);
    }

    public static LayoutManagerFactory grid(final int spanCount) {
        return recyclerView -> new GridLayoutManager(recyclerView.getContext(), spanCount);
    }

    public static LayoutManagerFactory grid(final int spanCount, @Orientation final int orientation, final boolean reverseLayout) {
        return recyclerView -> new GridLayoutManager(recyclerView.getContext(), spanCount, orientation, reverseLayout);
    }

    private LayoutManagers() {
    }
}