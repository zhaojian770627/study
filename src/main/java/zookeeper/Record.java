package zookeeper;

import java.io.IOException;

import org.apache.jute.InputArchive;
import org.apache.yetus.audience.InterfaceAudience;

/**
 * Interface that is implemented by generated classes.
 * 
 */
@InterfaceAudience.Public
public interface Record {
    public void serialize(OutputArchive archive, String tag)
        throws IOException;
    public void deserialize(InputArchive archive, String tag)
        throws IOException;
}
