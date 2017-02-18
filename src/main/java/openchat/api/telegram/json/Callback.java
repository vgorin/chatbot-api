package openchat.api.telegram.json;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 2/17/17 6:41 PM
 */


@XmlRootElement
public class Callback extends AbstractJson {
	@XmlElement
	public boolean ok;
	@XmlElement
	public List<Update> result;
}
