package xyz.xyz0z0.hosttools.data;

import io.reactivex.Observable;
import xyz.xyz0z0.hosttools.data.db.DatabaseSource;
import xyz.xyz0z0.hosttools.data.network.ApiHelperSource;
import xyz.xyz0z0.hosttools.data.network.model.ServiceInfoResponse;
import xyz.xyz0z0.hosttools.data.prefs.PreferencesSource;

public interface DataSource extends ApiHelperSource,PreferencesSource,DatabaseSource {

}
