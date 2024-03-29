/***
 * Copyright 2002-2010 jamod development team
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * Original implementation by jamod development team.
 * This file modified by Charles Hache <chache@brood.ca>
 ***/

package net.wimpi.modbus.msg;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.wimpi.modbus.Modbus;
import net.wimpi.modbus.ModbusCoupler;
import net.wimpi.modbus.procimg.IllegalAddressException;
import net.wimpi.modbus.procimg.ProcessImage;
import net.wimpi.modbus.procimg.Register;

/**
 * Class implementing a <tt>ReadMultipleRegistersRequest</tt>. The
 * implementation directly correlates with the class 0 function <i>read multiple
 * registers (FC 3)</i>. It encapsulates the corresponding request message.
 * 
 * @author Dieter Wimberger
 * @version @version@ (@date@)
 */
public final class DA555ReadID extends ModbusRequest {

	// instance attributes
	private int m_Reference;
	private int m_WordCount;

	/**
	 * Constructs a new <tt>ReadMultipleRegistersRequest</tt> instance.
	 */
	public DA555ReadID() {
		super();
		setFunctionCode(Modbus.DA555_READ_ID);
		// 4 bytes (remember unit identifier and function
		// code are excluded)
		setDataLength(2);
	}// constructor

	/**
	 * Constructs a new <tt>ReadMultipleRegistersRequest</tt> instance with a
	 * given reference and count of words to be read.
	 * <p>
	 * 
	 * @param ref
	 *            the reference number of the register to read from.
	 * @param count
	 *            the number of words to be read.
	 */
	public DA555ReadID(int ref) {
		super();
		setFunctionCode(Modbus.DA555_READ_ID);
		setDataLength(2);
		setReference(ref);
		setWordCount(11);
	}// constructor

	public ModbusResponse createResponse() {
		ReadMultipleRegistersResponse response = null;
		Register[] regs = null;

		// 1. get process image
		ProcessImage procimg = ModbusCoupler.getReference().getProcessImage();
		// 2. get input registers range
		try {
			regs = procimg.getRegisterRange(this.getReference(),
					this.getWordCount());
		} catch (IllegalAddressException iaex) {
			return createExceptionResponse(Modbus.ILLEGAL_ADDRESS_EXCEPTION);
		}
		response = new ReadMultipleRegistersResponse(regs);
		// transfer header data
		if (!isHeadless()) {
			response.setTransactionID(this.getTransactionID());
			response.setProtocolID(this.getProtocolID());
		} else {
			response.setHeadless();
		}
		response.setUnitID(this.getUnitID());
		response.setFunctionCode(this.getFunctionCode());
		response.setReference(m_Reference);

		return response;
	}// createResponse

	/**
	 * Sets the reference of the register to start reading from with this
	 * <tt>ReadMultipleRegistersRequest</tt>.
	 * <p>
	 * 
	 * @param ref
	 *            the reference of the register to start reading from.
	 */
	public void setReference(int ref) {
		m_Reference = ref;
	}// setReference

	/**
	 * Returns the reference of the register to to start reading from with this
	 * <tt>ReadMultipleRegistersRequest</tt>.
	 * <p>
	 * 
	 * @return the reference of the register to start reading from as
	 *         <tt>int</tt>.
	 */
	public int getReference() {
		return m_Reference;
	}// getReference

	/**
	 * Sets the number of words to be read with this
	 * <tt>ReadMultipleRegistersRequest</tt>.
	 * <p>
	 * 
	 * @param count
	 *            the number of words to be read.
	 */
	public void setWordCount(int count) {
		m_WordCount = count;
		// setChanged(true);
	}// setWordCount

	/**
	 * Returns the number of words to be read with this
	 * <tt>ReadMultipleRegistersRequest</tt>.
	 * <p>
	 * 
	 * @return the number of words to be read as <tt>int</tt>.
	 */
	public int getWordCount() {
		return 13;//m_WordCount;
	}// getWordCount

	public void writeData(DataOutput dout) throws IOException {
		//dout.writeShort(m_Reference); // Valentin
		//dout.writeShort(m_WordCount); // Valentin
	}// writeData

	public void readData(DataInput din) throws IOException {
		m_Reference = 0;//din.readUnsignedShort();
		m_WordCount = din.readUnsignedShort();
	}// readData

	public String toString() {
		return "DA555ReadID - Ref: " + m_Reference
				+ " Words: " + m_WordCount;
	}

}// class ReadMultipleRegistersRequest
