package mts.mtech.apiinterceptor.enums;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

/**
 * @author mitchellsevera
 * Created on 20/10/2025
 */
public enum Features implements Feature {
    /**
     * Togglz feature
     * this is a toggle library for java apps that enables and disables features at runtime without deploying new code.
     * */

    @Label("Game Of Thrones Feature")
    GAME_OF_THRONES_FEATURE,
    @Label("Bible Verses Feature")
    BIBLE_VERSES_FEATURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}
