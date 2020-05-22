/*
 * Copyright ©2018. Created by P. Bauer (p.bauer@htl-leonding.ac.at),
 * Department of Informatics and Media Technique, HTBLA Leonding,
 * Limesstr. 12 - 14, 4060 Leonding, AUSTRIA. All Rights Reserved. Permission
 * to use, copy, modify, and distribute this software and its documentation
 * for educational, research, and not-for-profit purposes, without fee and
 * without a signed licensing agreement, is hereby granted, provided that the
 * above copyright notice, this paragraph and the following two paragraphs
 * appear in all copies, modifications, and distributions. Contact the Head of
 * Informatics and Media Technique, HTBLA Leonding, Limesstr. 12 - 14,
 * 4060 Leonding, Austria, for commercial licensing opportunities.
 *
 * IN NO EVENT SHALL HTBLA LEONDING BE LIABLE TO ANY PARTY FOR DIRECT,
 * INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST
 * PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION,
 * EVEN IF HTBLA LEONDING HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * HTBLA LEONDING SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A
 * PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY,
 * PROVIDED HEREUNDER IS PROVIDED "AS IS". HTBLA LEONDING HAS NO OBLIGATION
 * TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 */

import org.junit.jupiter.api.Test;
import java.util.regex.Matcher;
import static org.junit.jupiter.api.Assertions.*;

public class HttpUrlCheckerTest {

    @Test
    public void testShortestPossible() {
        checkRegex("http://b.com");
    }

    private void checkRegex(final String httpString) {
        Matcher matcher = HttpUrlChecker.getMatcher(httpString);
        assertTrue(matcher.find());
        assertEquals(httpString, matcher.group());
        assertFalse(matcher.find());
    }

    @Test
    public void testMissingDomain() {
        Matcher matcher = HttpUrlChecker.getMatcher("http://tooshort");
        assertFalse(matcher.find());
    }

    @Test
    public void testDomainAndSubdomain() {
        checkRegex("http://www.bajupa.com");
    }

    @Test
    public void testMoreSubdomains() {
        checkRegex("http://www.flll.jku.ac.at");
    }

    @Test
    public void testDomainsWithDash() {
        checkRegex("http://www.htl-leonding.ac.at");
    }

    @Test
    public void testDomainWithNumbers() {
        checkRegex("http://456bereastreet.com");
    }

    @Test
    public void testDomainWithNumbersOnly() {
        checkRegex("http://37.com");
    }

    @Test
    public void testHttpsScheme() {
        checkRegex("https://google.com");
    }

    @Test
    public void testInvalidSchemeName() {
        Matcher matcher = HttpUrlChecker.getMatcher("htt://www.gmail.com");
        assertFalse(matcher.find());
    }

    @Test
    public void testHostWithHyphenAtBegin() {
        Matcher matcher = HttpUrlChecker.getMatcher("http://-37.com");
        assertFalse(matcher.find());
    }

    @Test
    public void testHostWithHyphenAtEnd() {
        Matcher matcher = HttpUrlChecker.getMatcher("http://37-.com");
        assertFalse(matcher.find());
    }

    @Test
    public void testDomainWithHyphenAtBegin() {
        Matcher matcher = HttpUrlChecker.getMatcher("http://www.-37.com");
        assertFalse(matcher.find());
    }

    @Test
    public void testDomainWithHyphenAtEnd() {
        Matcher matcher = HttpUrlChecker.getMatcher("http://www.37-.com");
        assertFalse(matcher.find());
    }


}