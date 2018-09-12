// This file was generated by Mendix Business Modeler.
//
// WARNING: Only the following code will be retained when actions are regenerated:
// - the import list
// - the code between BEGIN USER CODE and END USER CODE
// - the code between BEGIN EXTRA CODE and END EXTRA CODE
// Other code you write will be lost the next time you deploy the project.
// Special characters, e.g., é, ö, à, etc. are supported in comments.

package communitycommons.actions;

import com.google.common.collect.Lists;
import com.mendix.systemwideinterfaces.MendixRuntimeException;
import com.mendix.systemwideinterfaces.core.IContext;
import com.mendix.webui.CustomJavaAction;
import communitycommons.proxies.SanitizerPolicy;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 
 * This function should be applied to all HTML which is displayed in the browser, and can be entered by (untrusted) users.
 * 
 * - HTML: The html to sanitize
 *  - policy1... policy6: one or more values of SanitizerPolicy. You may leave these poliy parameters explicitly empty if you don't want to allow additional elements. (I.e. provide 'empty' without the quotes as parameter value)
 *   
 *  BLOCKS: Allows common block elements including <p>, <h1>, etc.
 *  FORMATTING: Allows common formatting elements including <b>, <i>, etc.
 *  IMAGES: Allows <img> elements from HTTP, HTTPS, and relative sources.
 *  LINKS: Allows HTTP, HTTPS, MAILTO and relative links
 *  STYLES: Allows certain safe CSS properties in style="..." attributes.
 *  TABLES: Allows commons table elements.
 *   
 *  For more information, visit:
 *   
 */
public class XSSSanitize extends CustomJavaAction<String>
{
	private String html;
	private communitycommons.proxies.SanitizerPolicy policy1;

	public XSSSanitize(IContext context, String html, String policy1)
	{
		super(context);
		this.html = html;
		this.policy1 = policy1 == null ? null : communitycommons.proxies.SanitizerPolicy.valueOf(policy1);
	}

	@Override
	public String executeAction() throws Exception
	{
		// BEGIN USER CODE
		if (StringUtils.isEmpty(html)) {
			return "";
		}
		List<SanitizerPolicy> policyParams = Lists.newArrayList(policy1)
				.stream()
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
		if (policyParams.isEmpty()) {
			throw new MendixRuntimeException("At least one policy is required");
		}
		return communitycommons.StringUtils.sanitizeHTML(html, policyParams);
		
		// END USER CODE
	}

	/**
	 * Returns a string representation of this action
	 */
	@Override
	public String toString()
	{
		return "XSSSanitize";
	}

	// BEGIN EXTRA CODE
	// END EXTRA CODE
}