package com.tcs.sgv.eis.dao;

import java.util.List;

/**
 * 
 * @author 602399
 * 
 */

public interface DDOInfoDAO 
{
	public List getDDOInfo(String lStrDDOCode, Long lIntLangId, Long lIntDBId);
	public List getDDOInfoByPost(Long lIntPostId, Long lIntLangId, Long lIntDBId);
	public List getDDOInfoByCardex(String lStrCardexNo, String lStrOfficeCode, Long lLngLangId, Long lLngDBId);
	public List getDDOInfoByNo(String lStrDDONo, String lDDOType, Long lIntLangId, Long lIntDBId);
	public List getBillOfficeForDDO(String lStrDDOCode, Long lLngLangId, Long lLngDBId);
    public List getTrsryOfficeForDDO(String lStrDDOCode, Long lLngLangId);
    
    public List getperdtls();
    public List gettempdtls();
    public List getpaydtls1(Long locId);
    public List getpaydtls(Long locId);
    public List getcommon(Long postId);
}
