package org.exoplatform.wiki.rendering.macro.html;

/*
 * Copyright (C) 2003-2012 eXo Platform SAS.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see<http://www.gnu.org/licenses/>.
 */

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.xwiki.component.annotation.Component;
import org.xwiki.rendering.block.Block;
import org.xwiki.rendering.block.RawBlock;
import org.xwiki.rendering.macro.AbstractMacro;
import org.xwiki.rendering.macro.MacroExecutionException;
import org.xwiki.rendering.syntax.Syntax;
import org.xwiki.rendering.syntax.SyntaxType;
import org.xwiki.rendering.transformation.MacroTransformationContext;

/**
 * Simple Html Macro.
 */
@Component("html")
public class HtmlMacro extends AbstractMacro<HtmlMacroParameters> {
	/**
	 * The description of the macro.
	 */
	private static final String DESCRIPTION = "Simple Html Macro to unescape parameter for eXo Wiki";

	/**
	 * The syntax representing the output of this macro (used for the RawBlock).
	 */
	private static final Syntax XHTML_SYNTAX = new Syntax(SyntaxType.XHTML,
			"1.0");

	/**
	 * Create and initialize the descriptor of the macro.
	 */
	public HtmlMacro() {
		super("Html", DESCRIPTION, HtmlMacroParameters.class);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xwiki.rendering.macro.Macro#execute(Object, String,
	 *      MacroTransformationContext)
	 */
	public List<Block> execute(HtmlMacroParameters parameters, String content,
			MacroTransformationContext context) throws MacroExecutionException {
		 StringBuilder sb = new StringBuilder();
		 sb.append(StringEscapeUtils.unescapeHtml(parameters.getParameter()));
		 RawBlock rawBlock = new RawBlock(sb.toString(), XHTML_SYNTAX);
		
		 return Collections.singletonList((Block) rawBlock);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.xwiki.rendering.macro.Macro#supportsInlineMode()
	 */
	public boolean supportsInlineMode() {
		return true;
	}
	
}
