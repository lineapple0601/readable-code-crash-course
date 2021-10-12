package readablecode.week3;

import java.util.List;
import java.util.Objects;

import com.google.common.base.Strings;

public class MarkdownTableUtils {
	// TODO3 : find the code to be replace with the method used at TODO7
	// refer OAOO principal

	// TODO4 : extract method and remove the comment if the comment is unnecessary,
	// 11.4, 5.1

	// TODO5 : refactor the extracted method by removing StringBuilder Argument and
	// return String
	// https://cyzennt.co.jp/blog/2021/05/19/java%EF%BC%9A%E5%BC%95%E6%95%B0%E3%81%A7%E6%B8%A1%E3%81%97%E3%81%9F%E5%8F%82%E7%85%A7%E5%9E%8B%E5%A4%89%E6%95%B0%E3%82%92%E3%83%A1%E3%82%BD%E3%83%83%E3%83%89%E5%86%85%E3%81%A7%E5%A4%89%E6%9B%B4/

	// TODO1 add @throws in javadoc
	// e.g @throws xxxException if xxx is null or is less than XXX
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
		// create header and empty rows
		String headerRows = createHeaderRows(headerRowCaptions);
		String emptyRows = createEmptyRows(headerRowCaptions, emptyRowCount);

		return (headerRows + emptyRows);
	}

	/**
	 * Return markdown table's empty rows string
	 * @param headerRowCaptions
	 * @param emptyRowCount
	 * @return String
	 */
	private static String createEmptyRows(List<String> headerRowCaptions, int emptyRowCount) {
		StringBuilder markdownTable = new StringBuilder();
		// create lines for empty rows
		for (int i = 0; i < emptyRowCount; i++) {
			markdownTable = createRow(markdownTable, headerRowCaptions, " ");
		}
		return markdownTable.toString();
	}

	/**
	 * Return markdown table's header-row & border-row string(2 lines)
	 * @param headerRowCaptions
	 * @param emptyRowCount
	 * @return String
	 */
	private static String createHeaderRows(List<String> headerRowCaptions) {
		StringBuilder markdownTable = new StringBuilder();
		// create header row
		markdownTable = createRow(markdownTable, headerRowCaptions);
		// create border row
		markdownTable = createRow(markdownTable, headerRowCaptions, "-");
		return markdownTable.toString();
	}
	
	/**
	 * Return markdown table's row with parameter
	 * It will append row string to stringbuilder that passing by parameter
	 * @param markdownTable
	 * @param headerRowCaptions
	 * @param value Optional Parameter
	 * @return StringBuilder
	 */
	private static StringBuilder createRow(StringBuilder markdownTable, List<String> headerRowCaptions, String... value) {
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
		return markdownTable;
	}
}
