package openchat.api.messenger.json;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vgorin
 *         file created on 11/19/16 2:45 PM
 */


@XmlRootElement
public class Read extends AbstractJson {
	@XmlElement
	public String watermark;
	@XmlElement
	public String seq;
}
