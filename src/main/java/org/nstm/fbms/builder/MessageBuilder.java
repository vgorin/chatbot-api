package org.nstm.fbms.builder;

import org.nstm.fbms.json.Message;

/**
 * A superclass for a variety of builders which build a {@link Message}
 *
 * @see CarouselBuilder
 * @see ListItemBuilder
 *
 * @author vgorin
 *         file created on 12/7/16 8:30 PM
 */


abstract class MessageBuilder extends GenericBuilder<Message> {
	protected Message message;

	@Override
	Message buildGeneric() {
		return message;
	}
}
