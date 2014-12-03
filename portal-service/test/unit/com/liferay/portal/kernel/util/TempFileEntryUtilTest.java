package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Kayleen Lim
 */

public class TempFileEntryUtilTest {

    @Test
    public void testGetTempFolderNameWithLongPortletId() throws Exception{
        String expected = "cabc7889bb678adaf651cbe68824ae7d4bfe0e859f00696c84549987207e5155";
        String test = TempFileEntryUtil.getTempFolderName("com.liferay.portal.kernel.lar.ExportImportHelpertestajaxportlet_WAR_ajaxtestportlet_INSTANCE_ovCXcIQL242L");
        Assert.assertEquals(expected, test);
    }

	@Test
	public void testGetTempFolderNameWithShortPortletId() throws Exception {
        String expected = "c04f8cdb685f7b905abc9e7e323c800ff8d08c2936c6b5ca153edfdd90850a0f";
        String test = TempFileEntryUtil.getTempFolderName("com.liferay.portal.kernel.lar.ExportImportHelper86");
        Assert.assertEquals(expected, test);
    }

    @Test
    public void testGetTempFolderNameWithNoPortletId() throws Exception {
        String expected = "1db25597ffc920e0a2e046daa0eb47f628da8797c0807e4027b8b5cb494f0d63";
        String test = TempFileEntryUtil.getTempFolderName("com.liferay.portal.kernel.lar.ExportImportHelper");
        Assert.assertEquals(expected, test);
    }

    private static Log _log = LogFactoryUtil.getLog(TempFileEntryUtilTest.class);
}