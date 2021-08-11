package wu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.apache.ibatis.javassist.bytecode.stackmap.TypeData.ClassName;
import org.apache.ibatis.javassist.expr.Instanceof;

import com.wu.domain.User;

public class kuan implements InvocationHandler {

	private User user;
	public void kuan(User user){
		this.user=user;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		StringBuilder sb=new StringBuilder(user.getUsername());
		sb.append("中国");
		Object result=method.invoke(args);
		return result;
	}
}