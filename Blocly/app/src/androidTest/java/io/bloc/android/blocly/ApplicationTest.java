package io.bloc.android.blocly;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.RenamingDelegatingContext;

import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<BloclyApplication> {
    public ApplicationTest () {
        super(BloclyApplication.class);


        @Override
        protected void setUp() throws Exception {
            super.setUp();
            setContext(new RenamingDelegatingContext(getContext(), "test_"));
            createApplication();
        }

    public void testApplicationHasDataSource () {
        BloclyApplication application = getApplication();
        application.onCreate();
        assertNotNull(application.getDataSource());
    }

    public void testApplicationImageLoaderInitialization () {
        BloclyApplication application = getApplication();
        application.onCreate();
        assertTrue(ImageLoader.getInstance().isInited());
    }
}
}