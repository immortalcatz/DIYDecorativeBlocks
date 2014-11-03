/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Ordinastie
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.malisis.ddb.renderer;

import java.util.ArrayList;
import java.util.List;

import net.malisis.core.renderer.BaseRenderer;
import net.malisis.ddb.block.DDBStairs;
import net.minecraft.util.AxisAlignedBB;

/**
 * @author Ordinastie
 *
 */
public class StairsRenderer extends BaseRenderer
{
	@Override
	protected void initialize()
	{
		rp.useBlockBounds.set(false);
		rp.renderAllFaces.set(true);
		rp.interpolateUV.set(true);
	}

	@Override
	public void render()
	{
		rp.useBlockBounds.set(false);

		List<AxisAlignedBB> list = null;
		if (renderType == TYPE_ISBRH_WORLD)
			list = ((DDBStairs) block).getBounds(world, x, y, z);
		else
		{
			list = new ArrayList<>();
			list.add(AxisAlignedBB.getBoundingBox(0, 0, 0, 1, 0.5F, 1));
			list.add(AxisAlignedBB.getBoundingBox(0, 0.5F, 0, 1, 1, 0.5F));
		}

		for (AxisAlignedBB aabb : list)
		{
			shape.resetState();
			rp.renderBounds.set(aabb);
			drawShape(shape, rp);
		}
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}
}
