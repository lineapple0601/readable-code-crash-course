package readablecode.week3;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Strings;

public class MarkdownTableUtils {
	/**
	 * Returns the string of table which has empty rows as Markdown table syntax.
	 * length of captions for separator cell and empty cell is same with their
	 * header captions
	 * 
	 * 
	 * @param headerRowCaptions the captions for header row
	 * @param emptyRowCount     the number of empty rows.
	 * @return the string of table which has empty rows as Markdown table
	 * @throw IllegalArgumentException	パラメータにエラーあり
	 * @throw NullPointerException	パラメータがNull
	 * 
	 */
	public static String createEmptyTable(List<String> headerRowCaptions, int emptyRowCount) {
		// validate args
		Objects.requireNonNull(headerRowCaptions, "headerCaptions must not be null");
		if (headerRowCaptions.isEmpty()) {
			throw new IllegalArgumentException("headerCaptions must have one more elements");
		}
		if (emptyRowCount < 1) {
			throw new IllegalArgumentException("emptyRowCount must be greater than or equal to 1");
		}
		
		// create header, border, empty rows
		String headerRows = createHeaderRows(headerRowCaptions);
		String borderRows = createBorderRows(headerRowCaptions);
		String emptyRows = createEmptyRows(headerRowCaptions, emptyRowCount);

		// return string joined all rows
		return (headerRows + borderRows + emptyRows);
	}

	/**
	 * Return markdown table's empty rows string
	 * @param headerRowCaptions
	 * @param emptyRowCount
	 * @return String
	 */
	private static String createEmptyRows(List<String> headerRowCaptions, int emptyRowCount) {
		StringBuilder markdownTable = new StringBuilder();
		
		// create lines for empty rows in markdownTable
		for (int i = 0; i < emptyRowCount; i++) {
			createRow(markdownTable, headerRowCaptions, " ");
		}
		
		return markdownTable.toString();
	}

	/**
	 * Return markdown table's header-row string
	 * @param headerRowCaptions
	 * @return String
	 */
	private static String createHeaderRows(List<String> headerRowCaptions) {
		StringBuilder markdownTable = new StringBuilder();
		
		// create header row in markdownTable
		createRow(markdownTable, headerRowCaptions);
		
		return markdownTable.toString();
	}
	
	/**
	 * Return markdown table's border-row string
	 * @param headerRowCaptions
	 * @return String
	 */
	private static String createBorderRows(List<String> headerRowCaptions) {
		StringBuilder markdownTable = new StringBuilder();
		
		// create border row in markdownTable
		createRow(markdownTable, headerRowCaptions, "-");
		
		return markdownTable.toString();
	}
	
	/**
	 * Return markdown table's row with parameter
	 * It will append row string to stringbuilder instance that passing by parameter
	 * @param markdownTable
	 * @param headerRowCaptions
	 * @param value Optional Parameter
	 * @return StringBuilder
	 */
	private static void createRow(StringBuilder markdownTable, List<String> headerRowCaptions, String... value) {
		// create row with parameter
		for (String e : headerRowCaptions) {
			markdownTable.append("|");
			// when value is not null and length is 1 -> repeat value's character
			// or else, repeat headerRowCaptions's header string
			if (value.length > 0 && value[0].length() == 1) {
				markdownTable.append(Strings.repeat(value[0], e.length()));
			} else {
				markdownTable.append(e);
			}
		}
		markdownTable.append("|");
		markdownTable.append(System.lineSeparator());
	}
}
