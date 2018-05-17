<#include "/macro.include"/>
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${command};

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ${basepackage}.${api}.${module}.${className}Service;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;
import ${basepackage}.${model}.JSON;
import ${basepackage}.${constant}.Constants;

@Service("${classNameLower}Command")
@Scope("prototype")
public class ${className}Command extends HystrixCommand<JSON> {
	@Autowired
	private ${className}Service ${classNameLower}Service;

	public ${className}Command() {
		super(setter());
	}

	private static Setter setter() {
		// 服务分组
		HystrixCommandGroupKey groupKey = HystrixCommandGroupKey.Factory
				.asKey("${className}Group");
		// 服务标识
		HystrixCommandKey commandKey = HystrixCommandKey.Factory
				.asKey("${className}");
		// 线程池名称
		HystrixThreadPoolKey threadPoolKey = HystrixThreadPoolKey.Factory
				.asKey("${className}-pool");
		// 线程池配置
		HystrixThreadPoolProperties.Setter threadPoolProperties = HystrixThreadPoolProperties
				.Setter().withCoreSize(10).withKeepAliveTimeMinutes(5)
				.withMaxQueueSize(Integer.MAX_VALUE)
				.withQueueSizeRejectionThreshold(10000);
		// 命令属性配置
		HystrixCommandProperties.Setter commandProperties = HystrixCommandProperties
				.Setter()
				.withExecutionIsolationStrategy(
						HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
				.withFallbackEnabled(true)// 默认true
				.withFallbackIsolationSemaphoreMaxConcurrentRequests(100)// 默认10
				.withExecutionIsolationThreadInterruptOnFutureCancel(false) // 默认false
				.withExecutionIsolationThreadInterruptOnTimeout(true)// 默认true
				.withExecutionTimeoutEnabled(true) // 默认true
				.withExecutionTimeoutInMilliseconds(1000)
				.withCircuitBreakerEnabled(true)//默认为true
				.withCircuitBreakerForceClosed(false)//默认为false
				.withCircuitBreakerForceOpen(false)//默认为false
				.withCircuitBreakerErrorThresholdPercentage(50)//默认为50%
				.withCircuitBreakerRequestVolumeThreshold(20) //默认为20
				.withCircuitBreakerSleepWindowInMilliseconds(5000);// 默认1000

		return HystrixCommand.Setter.withGroupKey(groupKey)
				.andCommandKey(commandKey).andThreadPoolKey(threadPoolKey)
				.andThreadPoolPropertiesDefaults(threadPoolProperties)
				.andCommandPropertiesDefaults(commandProperties);
	}

	@Override
	protected JSON run() throws Exception {
		return ${classNameLower}Service.query();
	}

	@Override
	protected JSON getFallback() {
		JSON json = new JSON();
		json.setCode(Constants.CODE_GENERAL_FAIL);
		json.setMessage(Constants.MSG_GENERAL_FAIL);
		return json;
	}
}