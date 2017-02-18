package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author vgorin
 *         file created on 11/19/16 2:45 PM
 */


@XmlRootElement
public class Delivery extends AbstractJson {
	@XmlElement
	public List<String> mids;
	@XmlElement
	public String watermark;
	@XmlElement
	public String seq;
}
